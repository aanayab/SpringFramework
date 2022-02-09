package com.truper.practica1.interfaces.operations.api.impl;

import com.truper.practica1.interfaces.operations.api.IKidsCalculator;

public class KidsCalculator extends Calculator<IKidsCalculator> implements IKidsCalculator {

	@Override
	public IKidsCalculator subtract(double number) {
		this.setAccumulator(this.getAccumulator() - number);
		return this;
	}

	@Override
	public IKidsCalculator add(double number) {
		this.setAccumulator(this.getAccumulator() + number);
		return this;
	}

}
