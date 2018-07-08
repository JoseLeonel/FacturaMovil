package com.emprendesoftcr.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.emprendesoftcr.repository.UsuarioRepository;
import com.emprendesoftcr.service.impl.CustomUsuariosDetailsService;

@EnableJpaRepositories(basePackageClasses = UsuarioRepository.class)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUsuariosDetailsService	usuarioDetailsService;

	
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
		web.ignoring().antMatchers("/administrativo/**","/templates/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/dist/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		http.authorizeRequests().antMatchers("/administrativo/**","/templates/**","/bootstrap/**", "/dist/**", "/plugins/**", "/resources/**", "/registration").permitAll().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/").usernameParameter("username").passwordParameter("password").and().logout().and().exceptionHandling().accessDeniedPage("/error/403");

		http.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.sessionManagement().invalidSessionUrl("/");
	}

}