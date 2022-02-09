package com.truper.spring.core.practica15.test.autowired.constructor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.autowired.constructor.bean.Engineer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredConstructorAnnotationTest {
	
	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-autowired-constructor-application-context.xml";
		ctx = new ClassPathXmlApplicationContext(ruta);
	}

	@After
	public void afterClass() {
		ctx.close();
	}

	@Test
	public void autowiredConstructorAnnotationTest() {

		log.info("autowiredConstructorAnnotationTest -------------------");

		// Implementar
		Engineer engineer = ctx.getBean(Engineer.class);

		Assert.assertNotNull(engineer);
		Assert.assertNotNull(engineer.getName());
		Assert.assertEquals("Lara Croft", engineer.getName());
		Assert.assertNull(engineer.getDni());
		Assert.assertNotNull(engineer.getLaptop());
		Assert.assertNotNull(engineer.getStylus());

		log.info("engineer: {}", engineer);
	}

}
