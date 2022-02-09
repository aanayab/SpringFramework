package com.truper.spring.core.practica18.jsr330.bean.api.impl;

import com.truper.spring.core.practica18.jsr330.bean.Budget;
import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.bean.api.IDirector;

import lombok.Data;

@Data
public class Director implements IDirector {

	private Employee employee;
	private Budget budget = new Budget();
}
