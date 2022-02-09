package com.truper.spring.validation.practica30.parte1.validator.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;

import com.truper.spring.validation.practica30.parte1.validator.PersonValidator;

@Configuration
public class ValidatorTestConfig {

	@Bean
	public Validator personValidator() {
		return new PersonValidator();
	}

	@Bean
	public MessageSource messageSource() { // este nombre es forzoso messageSource
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages");// messages_LOCALE (Locale => idioma_PAIS)
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}
}
