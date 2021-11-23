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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.emprendesoftcr.repository.UsuarioRepository;
import com.emprendesoftcr.service.impl.CustomUsuariosDetailsService;

@EnableJpaRepositories(basePackageClasses = UsuarioRepository.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final TokenProvider						tokenProvider;
	@SuppressWarnings("unused")
	private final CorsFilter							corsFilter;

	@Autowired
	private CustomUsuariosDetailsService	usuarioDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
	}

	public WebSecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter) {
		this.tokenProvider = tokenProvider;
		this.corsFilter = corsFilter;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/administrativo/**", "/templates/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/prueba/**", "/images/**", "/dist/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/administrativo/**", "/templates/**", "/fonts/**", "/bootstrap/**", "/dist/**", "/plugins/**", "/resources/**", "/registration").permitAll()

				.antMatchers("/api/cliente/ListarClientes.do").authenticated() // GET
				.antMatchers("/api/cliente/ListarClientesActivos.do").authenticated() // GET
				.antMatchers("/api/cliente/AgregarCliente.do").authenticated() // POST
				.antMatchers("/api/cliente/ModificarClienteAjax.do").authenticated() // POST
				.antMatchers("/api/cliente/MostrarClienteAjax.do").authenticated() // GET
				.antMatchers("/api/cliente/clienteHaciendaByCedula").authenticated()// GET

				.antMatchers("/api/categoria/ListarCategoriasAjax.do").authenticated() // GET
				.antMatchers("/api/categoria/AgregarCategoriaAjax.do").authenticated()// POST
				.antMatchers("/api/categoria/ModificarCategoriaAjax.do").authenticated()// POST
				.antMatchers("/api/categoria/MostrarCategoriaAjax.do").authenticated()// POST

				.antMatchers("/api/articulo/ListarArticuloAjax.do").authenticated() // POST
				.antMatchers("/api/articulo/ModificarArticuloAjax.do").authenticated() // POST
				.antMatchers("/api/articulo/AgregarArticuloAjax.do").authenticated()// POST
				.antMatchers("/api/articulo/CambiarPrecioAjax").authenticated()// POST es modificar todo el articulo
				.antMatchers("/api/articulo/CambiarPrecioArticulo.do").authenticated()// POST es para cambiar precio descripcion
				.antMatchers("/api/articulo/findArticuloByCodigojax.do").authenticated()// GET

				.antMatchers("/api/usuarioCajero/ListarUsuariosCajasAjax.do").authenticated()// GET
				.antMatchers("/api/usuarioCajero/aperturaCaja.do").authenticated()// POST
				.antMatchers("/api/usuarioCajero/CerrarUsuarioCajaAjax.do").authenticated()// POST

				.antMatchers("/api/factura/AgregarSalidaEntradaDineroAjax.do").authenticated()// POST
				.antMatchers("/api/factura/listarEntradasOrSalidas.do").authenticated().antMatchers("/api/factura/ListarFacturasEsperaActivasAjax").authenticated()// GET
				.antMatchers("/api/factura/mostrarVentaEnEspera").authenticated() // GET
				.antMatchers("/api/factura/CrearFacturaServiceAjax").authenticated()// POST
				.antMatchers("/api/factura/tipoCambioBancoCentral.do").authenticated()// GET

				.antMatchers("/api/authenticate").permitAll().antMatchers("/login").permitAll().antMatchers("https://api.hacienda.go.cr/").permitAll().antMatchers("https://api.hacienda.go.cr/indicadores/tc").permitAll().antMatchers("https://api.hacienda.go.cr/fe/ae").permitAll().antMatchers("/service/callback.do").permitAll().antMatchers("/webjars/**").permitAll().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/").and().httpBasic().and().apply(securityConfigurerAdapter()).and().logout().and().exceptionHandling().accessDeniedPage("/403").and().exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login"));

		http.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);

		http.headers().frameOptions().disable();
		http.sessionManagement().invalidSessionUrl("/");
	}

	public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

		public AjaxAwareAuthenticationEntryPoint(String loginUrl) {
			super(loginUrl);
		}

		@Override
		public void commence(

				HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
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

//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowCredentials(true);
//		configuration.addAllowedOrigin("*");
//		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("OPTIONS");
//		configuration.addAllowedMethod("HEAD");
//		configuration.addAllowedMethod("GET");
//		configuration.addAllowedMethod("PUT");
//		configuration.addAllowedMethod("POST");
//		configuration.addAllowedMethod("DELETE");
//		configuration.addAllowedMethod("PATCH");
//		source.registerCorsConfiguration("/**", configuration);
//		return new CorsFilter(source);
//	}
	private JWTConfigurer securityConfigurerAdapter() {
		return new JWTConfigurer(tokenProvider);
	}
}