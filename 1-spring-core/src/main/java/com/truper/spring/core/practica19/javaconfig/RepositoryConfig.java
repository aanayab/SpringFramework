package com.truper.spring.core.practica19.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.truper.spring.core.practica19.javaconfig.bean.DummyRepository;

//Habilitar Clase de configuracion
@Configuration
//Agregar Import de clases de Recurso xml
@ImportResource(locations = {"classpath:/spring/practica19/datasource-application-context.xml"})
public class RepositoryConfig {

	// Define Bean
	@Bean
	public DummyRepository dummyRepository(String connectionString) {
		return new DummyRepository(connectionString);
	}

	// Define Bean
	@Bean
	public DummyRepository dummyRepository2(String connectionString) {
		return new DummyRepository(connectionString);
	}
}
