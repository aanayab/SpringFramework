package com.truper.spring.core.practica21.methodreplacement.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SportCar {

	private String model;

	public int run() {
		int mills = 120;

		log.info("Sport car model: {} is running at: {}...", model, mills);

		return mills;
	}

	public void start() {
		log.info("Sport car model: {} is starting...", model);
	}
}
