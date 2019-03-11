	package com.emprendesoftcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
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
	
//	private static DataSource datasource;
//  
//  public static DataSource getDataSource()
//  {
//          if(datasource == null)
//          {
//                  HikariConfig config = new HikariConfig();
//                   
//          config.setJdbcUrl("jdbc:mysql://localhost:3306/pruebas?useSSL=false");
//          config.setUsername("root");
//          config.setPassword("root");
//
//          config.setMaximumPoolSize(1);
//          config.setAutoCommit(false);
//          config.addDataSourceProperty("cachePrepStmts", "true");
//          config.addDataSourceProperty("prepStmtCacheSize", "250");
//          config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//           
//          datasource = new HikariDataSource(config);
//          }
//          return datasource;
//  }
	


}
