package com.truper.practica1.interfaces.operations.api.impl;

import com.truper.practica1.interfaces.operations.api.ISimpleCalculator;

public class SimpleCalculator extends Calculator<ISimpleCalculator> implements ISimpleCalculator {

	@Override
	public ISimpleCalculator subtract(double number) {
		this.setAccumulator(this.getAccumulator() - number);
		return this;
	}

	@Override
	public ISimpleCalculator add(double number) {
		this.setAccumulator(this.getAccumulator() + number);
		return this;
	}

	@Override
	public ISimpleCalculator divide(double number) {
		this.setAccumulator(this.getAccumulator() / number);
		return this;
	}

	@Override
	public ISimpleCalculator multiply(double number) {
		this.setAccumulator(this.getAccumulator() * number);
		return this;
	}

}