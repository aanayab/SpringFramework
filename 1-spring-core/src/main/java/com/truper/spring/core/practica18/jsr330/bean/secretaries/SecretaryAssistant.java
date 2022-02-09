package com.truper.spring.core.practica18.jsr330.bean.secretaries;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.bean.Secretary;
import com.truper.spring.core.practica18.jsr330.qualifiers.SecretaryAssistantQualifier;

// Anotar como bean (singleton)
@Named
@Singleton
public class SecretaryAssistant extends Secretary {

	@Inject
	@SecretaryAssistantQualifier
	@Override
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
