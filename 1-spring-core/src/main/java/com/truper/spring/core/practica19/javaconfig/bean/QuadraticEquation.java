package com.truper.spring.core.practica19.javaconfig.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.truper.spring.core.practica19.javaconfig.bean.api.IQuadraticEquationService;
import com.truper.spring.core.practica19.javaconfig.bean.api.QuadraticEquationResult;

// Define Bean prototype
@Component
@Scope("prototype")
public class QuadraticEquation {

	public enum QuadraticEquationValue {
		Ax2, Bx, C
	}

	private double a;
	private double b;
	private double c;

	// Inyecta Dependencia
	@Autowired
	private IQuadraticEquationService quadraticService;

	public void setValue(double value, QuadraticEquationValue type) {
		switch (type) {
		case Ax2:
			this.a = value;
			break;
		case Bx:
			this.b = value;
			break;
		case C:
			this.c = value;
			break;

		default:
			break;
		}
	}

	public QuadraticEquationResult solve() {
		return this.quadraticService.solve(this.a, this.b, this.c);
	}

}
