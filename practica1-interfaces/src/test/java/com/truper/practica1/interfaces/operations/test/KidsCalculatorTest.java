package com.truper.practica1.interfaces.operations.test;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

import com.truper.practica1.interfaces.operations.api.IKidsCalculator;
import com.truper.practica1.interfaces.operations.api.impl.KidsCalculator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KidsCalculatorTest {

	private DecimalFormat df = new DecimalFormat("#.00##");

	@Test
	public void kidsCalculatorTest() {
		log.info("kidsCalculatorTest -------------------");

		// Implementar

		IKidsCalculator calculator = new KidsCalculator();

		double result = calculator.set(5).add(10).add(-15).subtract(1).result();

		Assert.assertEquals(-1D, result, 0.0001);
	}

}
