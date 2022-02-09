package com.truper.spring.core.practica18.jsr330.bean.employees.teamx;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.qualifiers.EmployeeQualifier;
import com.truper.spring.core.practica18.jsr330.qualifiers.EmployeeQualifier.EmployeeType;

// Anotar como bean (singleton)
@Named
@Singleton
@EmployeeQualifier(employeeType = EmployeeType.TESTER)
public class EmployeeTester extends Employee {

	public EmployeeTester() {
		this.name = "Team Leader Tester";
		this.dni = "AA-BB-CC-DD";
	}
}
