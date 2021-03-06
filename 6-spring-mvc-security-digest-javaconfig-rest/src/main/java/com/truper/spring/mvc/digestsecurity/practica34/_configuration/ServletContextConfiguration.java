package com.truper.spring.mvc.digestsecurity.practica34._configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration

// Habilita Spring Web MVC
@EnableWebMvc

// Habilita escaneo de beans sobre el paquete "com.truper.spring.mvc.digestsecurity.practica34.controller"
@ComponentScan(basePackages = "com.truper.spring.mvc.digestsecurity.practica34.controller")
public class ServletContextConfiguration {

	// Habilitar todos los Beans del WebApplicationContext
	
}