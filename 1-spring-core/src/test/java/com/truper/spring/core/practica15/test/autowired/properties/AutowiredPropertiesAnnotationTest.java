package com.truper.spring.core.practica15.test.autowired.properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.autowired.properties.bean.Reporter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredPropertiesAnnotationTest {

	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-autowired-property-application-context.xml";
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
		Reporter reporter = ctx.getBean(Reporter.class);

		Assert.assertNotNull(reporter);
		Assert.assertNotNull(reporter.getName());
		Assert.assertEquals("Lara Croft", reporter.getName());
		Assert.assertNull(reporter.getDni());
		Assert.assertNotNull(reporter.getBooklet());
		Assert.assertNotNull(reporter.getPencilYellow());

		log.info("reporter: {}", reporter);
	}

}
