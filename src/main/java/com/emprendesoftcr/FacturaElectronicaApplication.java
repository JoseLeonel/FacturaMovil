package com.emprendesoftcr;

import javax.sql.DataSource;
import javax.validation.Validator;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.support.ResourceTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackageClasses = { FacturaElectronicaApplication.class })
public class FacturaElectronicaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
	

		System.out.println("----------------- Inciando la Factura Electronica de EmprendeSoftCR 2018  ---------------------------");
		System.out.println("----------------- Desarrollador Jairo Cisneros  ---------------------------");
		System.out.println("----------------- Desarrollador Leonel Hernandez Chaverri  ---------------------------");
		SpringApplication.run(FacturaElectronicaApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FacturaElectronicaApplication.class);
	}
	
	

	 @Bean
   public TomcatConnectorCustomizer connectorCustomizer() {
       return new TomcatConnectorCustomizer() {
           @Override
           public void customize(Connector connector) {
           }
       };
   }

   @Bean
   public TomcatContextCustomizer contextCustomizer() {
       return new TomcatContextCustomizer() {
           @Override
           public void customize(Context context) {
           }
       };
   }
   @Bean
   public ResourceTransactionManager transactionManager(DataSource dataSource) {
       return new DataSourceTransactionManager(dataSource);
   }

   @Bean
   public Validator validator() {
       return new LocalValidatorFactoryBean();
   }



}
