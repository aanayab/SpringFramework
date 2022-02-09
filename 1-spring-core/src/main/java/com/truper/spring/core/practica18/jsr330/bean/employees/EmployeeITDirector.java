package com.truper.spring.core.practica18.jsr330.bean.employees;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;

// Anotar como bean
@Named("itDirectorEmployee")
@Singleton
public class EmployeeITDirector extends Employee {

	public EmployeeITDirector() {
		this.name = "Ivan Garcia";
		this.dni = "00-11-22-33";
	}
}
