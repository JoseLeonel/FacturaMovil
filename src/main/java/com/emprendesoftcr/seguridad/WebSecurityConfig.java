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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
		web.ignoring().antMatchers("/administrativo/**", "/templates/**","/resources/**", "/static/**","/css/**", "/js/**","/prueba/**", "/images/**", "/dist/**");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 http.authorizeRequests().antMatchers("/administrativo/**", "/templates/**", "/fonts/**","/bootstrap/**", "/dist/**", "/plugins/**", "/resources/**", "/registration")

		.permitAll().antMatchers("/service/CrearFacturaServiceAjax")
		//nuevo
		.permitAll().antMatchers("/ListarClientesAjax.do")
		.permitAll().antMatchers("/ListarClientesActivosAjax.do")
		.permitAll().antMatchers("/AgregarClienteAjax.do")
		.permitAll().antMatchers("/ModificarClienteAjax.do")
		.permitAll().antMatchers("/MostrarClienteAjax.do")
		.permitAll().antMatchers("/clienteHacienda.do")
		
		.permitAll().antMatchers("/ListarCategoriasAjax.do")
		.permitAll().antMatchers("/AgregarCategoriaAjax.do")
		.permitAll().antMatchers("/ModificarCategoriaAjax.do")
		.permitAll().antMatchers("/MostrarCategoriaAjax.do")
		
		
		.permitAll().antMatchers("/ListarArticuloAjax.do")
		.permitAll().antMatchers("/ModificarArticuloAjax.do")
		.permitAll().antMatchers("/AgregarArticuloAjax.do")
		.permitAll().antMatchers("/CambiarPrecioAjax")
		.permitAll().antMatchers("/CambiarPrecioArticulo.do")
		.permitAll().antMatchers("/findArticuloByCodigojax.do")
		
		.permitAll().antMatchers("/tipoCambioBancoCentral.do")
		
		.permitAll().antMatchers("/listarEntradasOrSalidas.do")
		.permitAll().antMatchers("/ListarCajasActivasAjax.do")
		.permitAll().antMatchers("/AgregarCajaAjax.do")
		.permitAll().antMatchers("/ModificarCajaAjax.do")
		.permitAll().antMatchers("/MostrarCajaAjax.do")
		.permitAll().antMatchers("/AgregarSalidaEntradaDineroAjax.do")
		.permitAll().antMatchers("/AgregarSalidaEntradaDineroAjax.do")
		
		
		.permitAll().antMatchers("/webjars/**")
		.permitAll().antMatchers("/login")
		.permitAll().antMatchers("http://localhost:8083/api-v1/get-all")
		.permitAll().antMatchers("http://localhost:8083/api-v1/repositorio/")
		.permitAll().antMatchers("https://api.hacienda.go.cr/")
		.permitAll().antMatchers("https://api.hacienda.go.cr/indicadores/tc")
		.permitAll().antMatchers("/service/callback.do").permitAll().antMatchers("/webjars/**").permitAll().antMatchers("/login")
		
		.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password")
		
		.and().logout().and().exceptionHandling().accessDeniedPage("/403")
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
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowCredentials(true);
	    configuration.addAllowedOrigin("*");
	    configuration.addAllowedHeader("*");
	    configuration.addAllowedMethod("OPTIONS");
	    configuration.addAllowedMethod("HEAD");
	    configuration.addAllowedMethod("GET");
	    configuration.addAllowedMethod("PUT");
	    configuration.addAllowedMethod("POST");
	    configuration.addAllowedMethod("DELETE");
	    configuration.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", configuration);
	    return new CorsFilter(source);
	}
	
}