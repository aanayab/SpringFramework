package com.truper.spring.core.practica18.jsr330.bean.employees.teamx;

import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.qualifiers.EmployeeQualifier;
import com.truper.spring.core.practica18.jsr330.qualifiers.EmployeeQualifier.EmployeeType;

// Anotar como bean (singleton)
@Named
@Singleton
@EmployeeQualifier(employeeType = EmployeeType.PROGRAMMER)
public class EmployeeProgrammer extends Employee {

	public EmployeeProgrammer() {
		this.name = "Team Leader Programmer";
		this.dni = "AA-BB-CC-DD";
	}
}
