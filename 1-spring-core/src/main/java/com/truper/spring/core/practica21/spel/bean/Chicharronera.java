package com.truper.spring.core.practica21.spel.bean;

import com.truper.spring.core.practica19.javaconfig.bean.api.QuadraticEquationResult;
import com.truper.spring.core.practica19.javaconfig.bean.api.impl.QuadraticEquationServiceImpl;

public class Chicharronera extends QuadraticEquationServiceImpl {

	public static QuadraticEquationResult calculate(double a, double b, double c) {

		Chicharronera chicharronera = new Chicharronera();

		return chicharronera.solve(a, b, c);
	}

}
