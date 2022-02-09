package com.truper.spring.core.practica18.test.jsr330;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.truper.spring.core.practica18.jsr330.bean.Corporation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Implementar run with spring-test
@RunWith(SpringRunner.class)
// cargar context configuration
@ContextConfiguration(locations = "classpath:/spring/practica18/annotations-jsr330-application-context.xml")
public class Jsr330AnnotationTest {

	// Inyectar
	@Inject
	private Corporation corporation;

	public void beforeClass() {
		Assert.assertNotNull(corporation);
	}

	@Test
	public void jsr330AnnotationTest() {

		log.info("jsr330AnnotationTest -------------------");

		Assert.assertNotNull(corporation);

		log.info("corporation: {}", corporation);
	}

}
