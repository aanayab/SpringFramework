package com.truper.spring.core.practica3.jugador;

import com.truper.spring.core.practica3.jugador.api.IJugador;
import com.truper.spring.core.practica3.liga.Partido;
import com.truper.spring.core.practica3.liga.Torneo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JugadorBaseball implements IJugador {

	private String nombre;

	@NonNull
	private Partido partido;

	@NonNull
	private Torneo torneo;

	public void saludar() {
		System.out.println("hola soy un jugador de Baseball");
		System.out.println("me llamo: " + this.nombre + " [" + Integer.toHexString(super.hashCode()) + "]");

	}

}
