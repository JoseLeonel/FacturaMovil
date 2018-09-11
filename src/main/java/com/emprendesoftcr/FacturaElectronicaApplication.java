package com.emprendesoftcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication()
@EnableAutoConfiguration 
@EnableScheduling
@ComponentScan(basePackageClasses = { FacturaElectronicaApplication.class })
public class FacturaElectronicaApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(FacturaElectronicaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FacturaElectronicaApplication.class);
	}
	
	 

}
