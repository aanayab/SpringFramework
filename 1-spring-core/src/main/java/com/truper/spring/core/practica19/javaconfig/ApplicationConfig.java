package com.truper.spring.core.practica19.javaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.truper.spring.core.practica19.javaconfig.bean.api.IQuadraticEquationService;
import com.truper.spring.core.practica19.javaconfig.bean.api.impl.QuadraticEquationServiceImpl;

// Habilitar Clase de configuracion
@Configuration
// Habilitar component scan
@ComponentScan(basePackages = "com.truper.spring.core.practica19.javaconfig", 
	excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, classes = Configuration.class) })

// Agregar Import de clases de configuracion Repository y Service
@Import({ ServiceConfig.class, RepositoryConfig.class })

public class ApplicationConfig {

	// Definir bean
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public IQuadraticEquationService quadraticService() {
		return new QuadraticEquationServiceImpl();
	}

	// Definir bean
	@Bean
	@Qualifier("quadraticEquationServiceBean2")
	public IQuadraticEquationService quadraticService2() {
		return new QuadraticEquationServiceImpl();
	}

	// Definir bean
	@Bean
	@Scope("prototype")
	@Qualifier("quadraticEquationServiceBean3")
	public IQuadraticEquationService quadraticService3() {
		return new QuadraticEquationServiceImpl();
	}

}
