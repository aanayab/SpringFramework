package com.truper.spring.core.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.truper.spring.core.practica18.jsr330.bean.Employee;
import com.truper.spring.core.practica18.jsr330.bean.api.IDirector;
import com.truper.spring.core.practica18.jsr330.bean.api.impl.Director;

@Configuration
@ComponentScan(basePackages = "com.truper.spring.core.practica18")
public class CorporationConfig {

	@Bean(name = "itDirectorX")
	@Qualifier("itDirector1")
	public IDirector itDirector(Employee itDirectorEmployee) {
		Director it = new Director();
		it.getBudget().setAmount(3500000D);
		it.setEmployee(itDirectorEmployee);
		return it;
	}

	@Bean
	public IDirector generalDirectorBean(Employee generalDirectorEmployee) {
		Director it = new Director();
		it.getBudget().setAmount(9000000D);
		it.setEmployee(generalDirectorEmployee);
		return it;
	}

}
