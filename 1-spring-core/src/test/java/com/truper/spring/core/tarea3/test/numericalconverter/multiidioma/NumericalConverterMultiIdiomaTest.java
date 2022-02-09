package com.truper.spring.core.tarea3.test.numericalconverter.multiidioma;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.truper.spring.core.tarea3.numericalconverter.multiidioma.NumericalConverter;
import com.truper.spring.core.tarea3.numericalconverter.multiidioma.config.NumericalConverterMultiIdiomaConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = NumericalConverterMultiIdiomaConfig.class)
public class NumericalConverterMultiIdiomaTest {

	private DecimalFormat currencyFormat = new DecimalFormat("$ ###,###,###.##");
	private DecimalFormat simpleNumberFormat = new DecimalFormat("#.##");

	@Autowired
	NumericalConverter converter;

	@Test
	public void numericalConverterTest() {

		log.info("numericalConverterTest -------------------");

		Assert.assertNotNull(converter);

		double number = Math.random() * 999_999_999;

		String numeroStr = simpleNumberFormat.format(number).toString();

		log.info("number: {}", currencyFormat.format(number).toString());
		log.info("letter number: {}", converter.convert(numeroStr, true));
	}

}
