package com.truper.spring.validation.practica30.parte1.test.validator;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.truper.spring.validation.practica30.parte1.domain.Person;
import com.truper.spring.validation.practica30.parte1.test.validator.utils.PersonValidatorUsTestUtils;
import com.truper.spring.validation.practica30.parte1.validator.config.ValidatorTestConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
// Asignar ValidatorTestConfig.class como clase de configuracion del contexto
@ContextConfiguration(classes = ValidatorTestConfig.class)

public class PersonValidatorUsTest {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private Validator personValidator;

	private Locale locale = new Locale("en", "US");

	@Before
	public void setUp() {
		Assert.assertNotNull(messageSource);
		Assert.assertNotNull(personValidator);
	}

	@Test
	public void validationPersonUsTest1() {

		log.info("validationPersonUsTest1 -------------------");

		Person person = new Person();
		// persona sin nombre ni edad.

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(2, errors.getErrorCount());

			PersonValidatorUsTestUtils.printErrors(errors, messageSource, locale);

		}

	}

	@Test
	public void validationPersonUsTest2() {

		log.info("validationPersonUsTest2 -------------------");

		Person person = new Person();
		// persona sin nombre y menor de edad.
		person.setAge(16);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(2, errors.getErrorCount());

			PersonValidatorUsTestUtils.printErrors(errors, messageSource, locale);
		}

	}

	@Test
	public void validationPersonUsTest3() {

		log.info("validationPersonUsTest3 -------------------");

		Person person = new Person();
		// persona con nombre y menor de edad.
		person.setName("Ivan");
		person.setAge(16);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		if (errors.hasErrors()) {

			Assert.assertEquals(1, errors.getErrorCount());

			PersonValidatorUsTestUtils.printErrors(errors, messageSource, locale);
		}

	}

	@Test
	public void validationPersonUsTest4() {

		log.info("validationPersonUsTest4 -------------------");

		Person person = new Person();
		// persona con nombre y mayor de edad.
		person.setName("Ivan");
		person.setAge(20);

		BindException errors = new BindException(person, Person.class.getName());

		ValidationUtils.invokeValidator(personValidator, person, errors);

		Assert.assertFalse(errors.hasErrors());
	}

}
