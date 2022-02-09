package com.truper.spring.core.practicaB.beanfactorypostprocessors.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Welcome {

	private String greetingsText;
	private String user;

	public void welcome() {
		log.info("Hi {}, {}", user, greetingsText);
	}
}
