package com.truper.spring.core.practica15.autowired.requiredFalse.qualifier.bean;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Airplane {

	// Inyeccion no requerida
	// @Autowired(required = false) // 1.

	// @Autowired
	// @Nullable // 2.

	@Autowired
	private @Setter(AccessLevel.NONE) Optional<String> airplaneCode;

	// Inyeccion requerida
	@Autowired
	// Inyectar un Airline especifica
	@Qualifier("airline2")
	private @Setter(AccessLevel.NONE) Airline airline;

}
