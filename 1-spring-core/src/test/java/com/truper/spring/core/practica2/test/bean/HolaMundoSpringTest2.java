package com.truper.spring.core.practica2.test.bean;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica2.bean.HolaMundo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest2 {

	@Test
	public void holaMundoSpringTest2() {
		log.info("holaMundoSpringTest2 -------------------------");

		String ruta = "spring/practica2/beans.xml";

		// Implementar IoC con ApplicationContext
		ApplicationContext context = new ClassPathXmlApplicationContext(ruta);

		HolaMundo hola = context.getBean("hola2", HolaMundo.class);

		Assert.assertNotNull(hola);
		Assert.assertNotNull(hola.getMensaje());
		Assert.assertEquals("Hola mundo", hola.getMensaje());
	}
}
