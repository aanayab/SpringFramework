package com.truper.spring.mvc.basicsecurity.practica33._configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration

// Habilita Spring Web MVC
@EnableWebMvc

// Habilita escaneo de beans sobre el paquete "com.truper.spring.mvc.basicsecurity.practica33.controller"
@ComponentScan(basePackages = "com.truper.spring.mvc.basicsecurity.practica33.controller")
public class ServletContextConfiguration {

	@Bean
	@Profile("with-views")
	public ViewResolver internalResourceViewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/jsps/", ".jsp");
	}

}