package com.truper.spring.core.practica3.test.jugador;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica3.jugador.JugadorFutbol;
import com.truper.spring.core.practica3.jugador.api.IJugador;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstadioSpringTest {

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "classpath:/spring/practica3/jugador-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) applicationContext).close();
	}

	@Test
	@Ignore
	public void estadioSpringTest1() {

		log.info("estadioSpringTest1 -------------------");

		// Implementar

		JugadorFutbol jugador = applicationContext.getBean("jugadorBean", JugadorFutbol.class);

		Assert.assertNotNull(jugador);
		Assert.assertNotNull(jugador.getNombre());
		Assert.assertNotNull(jugador.getPartido());
		Assert.assertNotNull(jugador.getPartido().getNombre());
		Assert.assertNotNull(jugador.getTorneo());
		Assert.assertNotNull(jugador.getTorneo().getNombre());
		Assert.assertNotNull(jugador.getTorneo().getEvento());
		Assert.assertNotNull(jugador.getTorneo().getEvento().getNombre());
	}

	@Test
	public void estadioSpringTest2() {
		log.info("estadioSpringTest2 -------------------");

		// Implementar
		IJugador jugador = applicationContext.getBean("jugadorBean", IJugador.class);

		Assert.assertNotNull(jugador);
		Assert.assertNotNull(jugador.getNombre());
		Assert.assertNotNull(jugador.getPartido());
		Assert.assertNotNull(jugador.getPartido().getNombre());
		Assert.assertNotNull(jugador.getTorneo());
		Assert.assertNotNull(jugador.getTorneo().getNombre());
		Assert.assertNotNull(jugador.getTorneo().getEvento());
		Assert.assertNotNull(jugador.getTorneo().getEvento().getNombre());

		jugador.saludar();
	}

}
