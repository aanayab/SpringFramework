package com.truper.spring.core.practica19.javaconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.truper.spring.core.practica19.javaconfig.bean.DummyRepository;
import com.truper.spring.core.practica19.javaconfig.bean.DummyService;

//Habilitar Clase de configuracion
@Configuration
public class ServiceConfig {

	// Define Bean
	@Bean
	public DummyService dummyService(@Qualifier("dummyRepository") DummyRepository dummyRespository) {
		return new DummyService(dummyRespository);
	}

	// Define Bean
	@Bean
	public DummyService dummyService2(@Qualifier("dummyRepository2") DummyRepository dummyRespository) {
		return new DummyService(dummyRespository);
	}
}
