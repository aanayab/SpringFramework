package com.truper.spring.core.practica16.jsr250.bean;

import javax.annotation.Resource;

import lombok.Data;

@Data
public class Subject {
	// Inyectar
	@Resource(name = "math")
	private String name;

	private String credits;
}
