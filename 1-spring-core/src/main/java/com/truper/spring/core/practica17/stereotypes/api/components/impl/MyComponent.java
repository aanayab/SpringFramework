package com.truper.spring.core.practica17.stereotypes.api.components.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.truper.spring.core.practica17.stereotypes.api.IComponentClass;

import lombok.Data;

@Data
// Anotar componente, implementar Interface IComponentClass
@Component
public class MyComponent implements IComponentClass {

	// Inyectar con "resource" bean componentClassName
	@Resource(name = "componentClassName")
	private String name;
}
