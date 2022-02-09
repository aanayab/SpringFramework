package com.truper.spring.core.practicaC.filteringcomponents.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
// Anotar como bean
@Component
public class Bike {

	// Inyectar
	@Value("KTM Super Duke 1290")
	public String model;
}
