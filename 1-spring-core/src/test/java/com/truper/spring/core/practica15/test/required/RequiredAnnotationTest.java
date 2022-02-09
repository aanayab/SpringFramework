package com.truper.spring.core.practica15.test.required;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.required.bean.Cameraman;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequiredAnnotationTest {

	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-required-application-context.xml";
		ctx = new ClassPathXmlApplicationContext(ruta);
	}

	@After
	public void afterClass() {
		ctx.close();
	}

	@Ignore
	@Test
	public void requiredAnnotationTest() {

		log.info("requiredAnnotationTest -------------------");

		// Implementar
		Cameraman okCameraman = ctx.getBean("okCameraman", Cameraman.class);

		Assert.assertNotNull(okCameraman);
		Assert.assertNotNull(okCameraman.getName());
		Assert.assertEquals("Lara Croft", okCameraman.getName());
		Assert.assertNotNull(okCameraman.getDni());
		Assert.assertEquals("DNI-1322-QWERTY", okCameraman.getDni());

		log.info("okCameraman: {}", okCameraman);
	}

	@Ignore
	@Test(expected = BeanCreationException.class) // En Spring 5 @Required esta depreciada
	public void badRequiredAnnotationTest() {

		log.info("badRequiredAnnotationTest -------------------");

		// Implementar
		Cameraman badCameraman = ctx.getBean("badCameraman", Cameraman.class);

		Assert.fail("Should fail at this line");
	}

}
