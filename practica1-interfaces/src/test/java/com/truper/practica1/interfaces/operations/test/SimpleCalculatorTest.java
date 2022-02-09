package com.truper.practica1.interfaces.operations.test;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

import com.truper.practica1.interfaces.operations.api.ISimpleCalculator;
import com.truper.practica1.interfaces.operations.api.impl.SimpleCalculator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleCalculatorTest {

	private DecimalFormat df = new DecimalFormat("#.00##");

	@Test
	public void kidsCalculatorTest() {
		log.info("kidsCalculatorTest -------------------");

		// Implementar

		ISimpleCalculator calculator = new SimpleCalculator();

		double result = calculator.set(5).add(10).add(-15).subtract(1).multiply(-1).divide(1).result();

		Assert.assertEquals(1D, result, 0.0001);
	}

}
