package com.truper.spring.core.practica15.test.autowired.requiredFalse.qualifier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.autowired.requiredFalse.qualifier.bean.Airplane;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredRequredFalseQualifierAnnotationTest {

	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-autowired-requiredFalse-qualifier-application-context.xml";
		ctx = new ClassPathXmlApplicationContext(ruta);
	}

	@After
	public void afterClass() {
		ctx.close();
	}

	@Test
	public void autowiredPropertiesAnnotationTest() {

		log.info("autowiredPropertiesAnnotationTest -------------------");

		// Implementar
		Airplane airplane = ctx.getBean(Airplane.class);

		Assert.assertNotNull(airplane);
		Assert.assertFalse(airplane.getAirplaneCode().isPresent()); // aqui es cambio
		Assert.assertNotNull(airplane.getAirline());
		Assert.assertNotNull(airplane.getAirline().getName());
		Assert.assertEquals("American Airlines", airplane.getAirline().getName());

		log.info("airplane: {}", airplane);
	}

}
