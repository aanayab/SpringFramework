package com.truper.spring.core.tarea3.numericalconverter.multiidioma.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.truper.spring.core.tarea3.numericalconverter.multiidioma.Currency;
import com.truper.spring.core.tarea3.numericalconverter.multiidioma.Numerical;
import com.truper.spring.core.tarea3.numericalconverter.multiidioma.NumericalConverter;

@Configuration
//@ImportResource({ "classpath:/spring/tarea3/numerical-converter-multi-idioma-application-context.xml" })
public class NumericalConverterMultiIdiomaConfig {

	@Bean
	public Currency currency(@Value("peso") String singularCurrency, @Value("pesos") String pluralCurrency) {
		
		return Currency.builder()
				.singularCurrency(singularCurrency)
				.pluralCurrency(pluralCurrency)
				.build();
	}
	
	@Bean
	public Numerical numerical() {
		return new Numerical();
	}
	
	@Bean
	public NumericalConverter numericalConverter(Currency currency) {
		
		NumericalConverter nc = new NumericalConverter(numerical());
		nc.setCurrrency(currency);
		
		return nc;
	}
}
