package com.truper.spring.core.practica3.test.jugador;

import org.aspectj.util.PartialOrder;
import org.junit.Assert;
import org.junit.Test;

import com.truper.spring.core.practica3.jugador.JugadorFutbol;
import com.truper.spring.core.practica3.liga.Evento;
import com.truper.spring.core.practica3.liga.Partido;
import com.truper.spring.core.practica3.liga.Torneo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EstadioNoSpringTest {

	@Test
	public void estadioNoSpringTest() {
		log.info("estadioNoSpringTest -------------------");

		// Implementar Test
		
		Evento evento = new Evento();
		evento.setNombre("Mundial Qatar 2022");
		
		Torneo torneo = new Torneo(evento);
		torneo.setNombre("4tos de Final");

		Partido partido = new Partido();
		partido.setNombre("Mexico vs Australia");
		
		JugadorFutbol jugador = new JugadorFutbol(partido, torneo);
		jugador.setNombre("Chicharito");
		

		Assert.assertNotNull(jugador);
		Assert.assertNotNull(jugador.getNombre());
		Assert.assertNotNull(jugador.getPartido());
		Assert.assertNotNull(jugador.getPartido().getNombre());
		Assert.assertNotNull(jugador.getTorneo());
		Assert.assertNotNull(jugador.getTorneo().getNombre());
		Assert.assertNotNull(jugador.getTorneo().getEvento());
		Assert.assertNotNull(jugador.getTorneo().getEvento().getNombre());
	}

}
