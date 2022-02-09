package com.truper.spring.mvc.jwtsecurity.practica35._configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.truper.spring.mvc.jwtsecurity.practica35.controller.PathController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = PathController.class)
public class ServletContextConfiguration {
 
}