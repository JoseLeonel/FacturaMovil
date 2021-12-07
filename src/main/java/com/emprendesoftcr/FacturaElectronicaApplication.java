package com.emprendesoftcr;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = { FacturaElectronicaApplication.class })
public class FacturaElectronicaApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	@PostConstruct
	public void init() { 
		TimeZone.setDefault(TimeZone.getTimeZone("America/Costa_Rica"));
	}	 
	
	public static void main(String[] args) {
		System.out.println("----------------- Inciando la Factura Electronica de EmprendeSoftCR 2018-09-01  ---------------------------");
		System.out.println("----------------- Desarrollador Leonel Hernandez Chaverri  ---------------------");
		System.out.println("----------------- Viviana Martinez Granados Telefono: 87292998/83124207 -----------------------");
		System.out.println("----------------- "
				+ "Juan 3:16 Porque de tal manera amo Dios al mundo "
				+ "que ha dado a su Hijo al mundo para que todo aquel que cree en EL "
				+ "no se pierda y tenga vida Eterna  ---------------------------");
		SpringApplication.run(FacturaElectronicaApplication.class, args);
		
	}
	

	 @Bean
   public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
       return new DeviceResolverHandlerInterceptor();
   }

   @Bean
   public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
       return new DeviceHandlerMethodArgumentResolver();
   }
   
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(deviceResolverHandlerInterceptor());
   }

   @Override
   public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
       argumentResolvers.add(deviceHandlerMethodArgumentResolver());
   }

}
