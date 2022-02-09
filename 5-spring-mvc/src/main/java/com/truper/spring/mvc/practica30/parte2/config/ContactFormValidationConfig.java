package com.truper.spring.mvc.practica30.parte2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import com.truper.spring.mvc.practica30.parte2.validator.ContactFormValidator;

@Configuration
@ComponentScan("com.truper.spring.mvc.practica30.parte2.controller")
public class ContactFormValidationConfig {

	@Bean
	public Validator contactFormValidator() {
		return new ContactFormValidator();
	}

}
