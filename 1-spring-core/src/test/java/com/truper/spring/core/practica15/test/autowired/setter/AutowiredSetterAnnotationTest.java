package com.truper.spring.core.practica15.test.autowired.setter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.autowired.setter.bean.Journalist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredSetterAnnotationTest {

	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-autowired-setter-application-context.xml";
		ctx = new ClassPathXmlApplicationContext(ruta);
	}

	@After
	public void afterClass() {
		ctx.close();
	}

	@Test
	public void autowiredSetterAnnotationTest() {

		log.info("autowiredSetterAnnotationTest -------------------");

		// Implementar
		Journalist journalist = ctx.getBean(Journalist.class);
		
		Assert.assertNotNull(journalist);
		Assert.assertNotNull(journalist.getName());
		Assert.assertEquals("Lara Croft", journalist.getName());
		Assert.assertNull(journalist.getDni());
		Assert.assertNotNull(journalist.getNotebook());
		Assert.assertNotNull(journalist.getPen());

		log.info("journalist: {}", journalist);
	}

}
