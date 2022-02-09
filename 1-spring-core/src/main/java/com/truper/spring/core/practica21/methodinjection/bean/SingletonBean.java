package com.truper.spring.core.practica21.methodinjection.bean;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.truper.spring.core.practica21.methodinjection.bean.api.IProcessor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// Define componente singleton
@Component
@Singleton
public abstract class SingletonBean {

	// Inyectar valor 3
	@Value("3")
	private @Setter Integer iterations;

	// No se puede tener una dependencia prototype en un singleton....

	public String process(String data) {
		log.info("SingletonBean id [{}]: sending to process data ...", super.hashCode());

		IProcessor processor = getProcessor();

		return processor.processData(data, iterations);
	}

	@Lookup("stringProcessor")
	public abstract IProcessor getProcessor();

}
// Define la estructura para inyectar metodos en este bean