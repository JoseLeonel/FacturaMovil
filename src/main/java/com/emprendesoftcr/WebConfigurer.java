package com.emprendesoftcr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
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
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = initCorsConfiguration();
		if (!CollectionUtils.isEmpty(config.getAllowedOrigins())) {
			log.debug("Registering CORS filter");
			source.registerCorsConfiguration("/api/**", config);
			source.registerCorsConfiguration("/management/**", config);
			source.registerCorsConfiguration("/v2/api-docs", config);
			source.registerCorsConfiguration("/v3/api-docs", config);
			source.registerCorsConfiguration("/swagger-resources", config);
			source.registerCorsConfiguration("/swagger-ui/**", config);
		}
		return new CorsFilter(source);
	}

	public CorsConfiguration initCorsConfiguration() {
		List<String> allowedOrigins = new ArrayList<String>(
				Arrays.asList("http://localhost:8085", "http://localhost:9000", "http://localhost:4200"));
		List<String> allowedMethods = new ArrayList<String>(Arrays.asList("*"));
		List<String> allowedHeaders = new ArrayList<String>(Arrays.asList("*"));
		List<String> exposedHeaders = new ArrayList<String>(Arrays.asList("Authorization", "Link", "X-Total-Count",
				"X-proyectoBaseApp-alert", "X-proyectoBaseApp-error", "X-proyectoBaseApp-params"));

		CorsConfiguration config = new CorsConfiguration();
	//	config.setAllowedOrigins(allowedOrigins);
		config.setAllowedMethods(allowedMethods);
		config.setAllowedHeaders(allowedHeaders);
		config.setExposedHeaders(exposedHeaders);
		config.setAllowCredentials(true);
		config.setMaxAge(1800L);
		return config;
	}

}
