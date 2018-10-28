package com.emprendesoftcr;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender mailSender() {
		/*JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("josehernandezchaverri@gmail.com");
		mailSender.setPassword("simo9292");

		Properties javaMailProperties = new Properties();

		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);

		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", true);
		javaMailProperties.put("mail.smtp.connectiontimeout", 20000);
		mailSender.setJavaMailProperties(javaMailProperties);

		*/
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail.emprendesoftcr.com");
		mailSender.setPort(587);
		mailSender.setUsername("gobookingcr@emprendesoftcr.com");
		mailSender.setPassword("simo9090");

		Properties javaMailProperties = new Properties();

		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", true);

		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.debug", true);
		javaMailProperties.put("mail.smtp.connectiontimeout", 10000);
		javaMailProperties.put("mail.smtp.ssl.trust", "mail.emprendesoftcr.com");

		mailSender.setJavaMailProperties(javaMailProperties);

		
		
		return mailSender;
	}
	
	@Bean
public 	VelocityEngine velocityEngine() throws IOException{
	    Properties properties = new Properties();
	    properties.load(this.getClass().getResourceAsStream("/application.properties"));
	    return new VelocityEngine(properties);
	}


}
