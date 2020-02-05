package com.emprendesoftcr.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.emprendesoftcr.repository.UsuarioRepository;
import com.emprendesoftcr.service.impl.CustomUsuariosDetailsService;

@EnableJpaRepositories(basePackageClasses = UsuarioRepository.class)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUsuariosDetailsService usuarioDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/administrativo/**", "/templates/**",  "/webjars/**","/resources/**", "/fonts/**","/static/**", "/css/**", "/js/**","/prueba/**", "/images/**", "/dist/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 http.authorizeRequests().antMatchers("/administrativo/**", "/templates/**", "/bootstrap/**", "/dist/**", "/plugins/**", "/resources/**", "/registration")

		.permitAll().antMatchers("/service/CrearFacturaServiceAjax")
		.permitAll().antMatchers("/movil/ListarClientesAjax.do")
		.permitAll().antMatchers("/movil/ListarCategoriasAjax.do")
		.permitAll().antMatchers("/movil/ListarArticulosAjax.do")
		.permitAll().antMatchers("/webjars/**")
		.permitAll().antMatchers("/login")
		.permitAll().antMatchers("https://api.hacienda.go.cr/")
		.permitAll().antMatchers("https://api.hacienda.go.cr/indicadores/tc")
		.permitAll().antMatchers("/service/callback.do").permitAll().antMatchers("/webjars/**").permitAll().antMatchers("/login")
		.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/").usernameParameter("username").passwordParameter("password")
		.and().logout().and().exceptionHandling().accessDeniedPage("/error/403")
		.and().exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"));
		http.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.sessionManagement().invalidSessionUrl("/");
	}

	public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint     {
	    public AjaxAwareAuthenticationEntryPoint(String loginUrl) {
	        super(loginUrl);
	    }

	    @Override
	    public void commence(
	    		
	        HttpServletRequest request,
	        HttpServletResponse response,
	        AuthenticationException authException)
	        throws IOException, ServletException {
	        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
	        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
	        if (isAjax) {
	    				response.setHeader("UNAUTHORIZED", "true");// Utilizado para los llamados ajax
	    				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Ajax REquest Denied (Session Expired)");
	        } else {
	            super.commence(request, response, authException);
	        }
	    }
	}
	
	public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**");
  }
	
	
}