package com.truper.spring.core.practica18.jsr330.bean.employees;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.qualifiers.SecretaryAssistantQualifier;

// Anotar como bean
@Named
@Singleton
@SecretaryAssistantQualifier
public class EmployeeSecretaryAssistant extends Employee {

	public EmployeeSecretaryAssistant() {
		this.name = "Alejandra Moras";
		this.dni = "AA-BB-CC-DD";
	}
}
