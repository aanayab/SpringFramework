package com.truper.spring.core.practica2.test.bean;

import org.junit.Assert;
import org.junit.Test;

import com.truper.spring.core.practica2.bean.HolaMundo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoNoSpringTest {

	@Test
	public void holaMundoNoSpringTest() {
		log.info("holaMundoNoSpringTest -------------------------");

		// Crear instancia de HolaMundo
		HolaMundo hola = new HolaMundo(); // invocamos constructor

		// seteamos o "inyectamos" de forma manual (por setter) la dependencia String
		// nombre;
		hola.setMensaje("Hola mundo");

		Assert.assertNotNull(hola);
		Assert.assertNotNull(hola.getMensaje());
		Assert.assertEquals("Hola mundo", hola.getMensaje());

	}

	@Test
	public void holaMundoNoSpringTest2() {
		log.info("holaMundoNoSpringTest2 -------------------------");

		// Crear instancia de HolaMundo
		// construimos o "inyectamos" de forma manual (por constructor) la dependencia
		// String nombre;
		HolaMundo hola = new HolaMundo("Hola mundo"); // invocamos constructor

		Assert.assertNotNull(hola);
		Assert.assertNotNull(hola.getMensaje());
		Assert.assertEquals("Hola mundo", hola.getMensaje());

	}
}
