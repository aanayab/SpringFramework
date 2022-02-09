package com.truper.spring.core.practica18.jsr330.bean.employees;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.qualifiers.ManagerEmployeeQualifier;

// Anotar como bean
@Named
@Singleton
@ManagerEmployeeQualifier
public class EmployeeManager extends Employee {

	public EmployeeManager() {
		this.name = "Ilse Hernandez";
		this.dni = "11-44-77-55";
	}
}
