package com.truper.spring.core.practica18.jsr330.bean.employees;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;

// Anotar como bean
@Named("generalDirectorEmployee")
@Singleton
public class EmployeeGeneralDirector extends Employee {

	public EmployeeGeneralDirector() {
		this.name = "Jorge Garcia";
		this.dni = "55-66-99-88";
	}
}
