package com.truper.spring.core.practica13.test.autowire.jugador;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica13.autowire.jugador.bean.JugadorFutbol;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowireJugadorTest {

	@Test
	public void autowireByNameTest() {

		log.info("autowireByNameTest -------------------");

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica13/jugador-autowire-application-context.xml");

		JugadorFutbol jugador = applicationContext.getBean(JugadorFutbol.class);

		Assert.assertNotNull(jugador);
		Assert.assertNotNull(jugador.getPartido());
		Assert.assertNotNull(jugador.getTorneo());
		Assert.assertNotNull(jugador.getTorneo().getEvento());
		Assert.assertEquals("Kikin Fonseca", jugador.getNombre());
		Assert.assertEquals("Santos vs Toluca", jugador.getPartido().getNombre());
		Assert.assertEquals("Final Ida", jugador.getTorneo().getNombre());
		Assert.assertEquals("Clausura 2021", jugador.getTorneo().getEvento().getNombre());

		log.info("jugador: {}", jugador);

		((AbstractApplicationContext) applicationContext).close();
	}

}
