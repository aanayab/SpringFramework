package com.truper.spring.core.practica3.liga;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Torneo {

	private String nombre;
	
	@NonNull
	private Evento evento;

}
