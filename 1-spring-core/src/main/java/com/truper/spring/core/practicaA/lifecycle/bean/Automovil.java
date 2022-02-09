package com.truper.spring.core.practicaA.lifecycle.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.SmartLifecycle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
// Implementar SmartLifecycle
public abstract class Automovil implements SmartLifecycle, BeanNameAware {

	private String engine;
	private String model;
	private String beanName;
	private boolean isRunning;

	public void run() {
		log.info("Automovil({}) model: {} run() ...",beanName, model);
	}

	@Override
	public void start() {
		log.info("Automovil is starting");
		isRunning = true;
	}

	@Override
	public void stop() {
		log.info("Automovil is stoping");
		isRunning = false;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

}
