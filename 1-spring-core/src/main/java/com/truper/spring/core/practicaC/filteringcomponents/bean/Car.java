package com.truper.spring.core.practicaC.filteringcomponents.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
// Anotar como servicio
@Component
public class Car {

	// Inyectar
	@Value("CLA 45 AMG")
	public String model;
}
