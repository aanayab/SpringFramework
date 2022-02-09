package com.truper.spring.core.practica18.jsr330.bean;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.qualifiers.ManagerEmployeeQualifier;

import lombok.Data;

@Data
//Anotar como bean (singleton)
@Named
@Singleton
public class Manager {

	// Inyectar
	@Inject
	@ManagerEmployeeQualifier
	private Employee employee;

	// Inyectar
	@Inject
	private Team team1;

	// Inyectar
	@Inject
	private Team team2;
}
