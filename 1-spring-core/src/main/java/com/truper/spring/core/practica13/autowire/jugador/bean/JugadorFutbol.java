package com.truper.spring.core.practica13.autowire.jugador.bean;

import com.truper.spring.core.practica13.autowire.jugador.liga.bean.Partido;
import com.truper.spring.core.practica13.autowire.jugador.liga.bean.Torneo;

import lombok.Data;

@Data
public class JugadorFutbol {
	private String nombre;
	private Torneo torneo;
	private Partido partido;

	public void saludar() {
		System.out.println("hola soy un jugador de Futbol");
		System.out.println("me llamo: " + this.nombre + " ["
				+ Integer.toHexString(super.hashCode()) + "]");

	}
}
