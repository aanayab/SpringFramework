package com.truper.practica1.interfaces.operations.api.impl;

import com.truper.practica1.interfaces.operations.api.ICalculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class Calculator<K> implements ICalculator<K> {

	@Getter(AccessLevel.PROTECTED)
	@Setter(AccessLevel.PROTECTED)
	private double accumulator;

	@Override
	public K set(double number) {
		this.accumulator = number;

		@SuppressWarnings("unchecked")
		K k = (K) this; // ClassCastException

		return k;
	}

	@Override
	public double result() {
		return this.accumulator;
	}

}
