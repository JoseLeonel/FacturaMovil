package com.emprendesoftcr;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class WebConfigurer implements ServletContextInitializer {

	private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);
	private final Environment env;

	public WebConfigurer(Environment env) {
		this.env = env;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if (env.getActiveProfiles().length != 0) {
			log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
		}

		log.info("Web application fully configured");
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
