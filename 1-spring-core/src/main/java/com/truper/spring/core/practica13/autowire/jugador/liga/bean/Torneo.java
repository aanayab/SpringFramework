package com.truper.spring.core.practica13.autowire.jugador.liga.bean;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Torneo {
	private @Setter(AccessLevel.NONE) String nombre;
	private @Setter(AccessLevel.NONE) Evento evento;
}
