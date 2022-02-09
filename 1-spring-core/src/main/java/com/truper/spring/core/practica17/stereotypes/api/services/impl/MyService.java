package com.truper.spring.core.practica17.stereotypes.api.services.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.truper.spring.core.practica17.stereotypes.api.IServiceClass;

import lombok.Data;

@Data
// Anotar servicio, implementar Interface IServiceClass
@Service
@Scope("prototype")
public class MyService implements IServiceClass {

	// Inyectar con "resource" bean serviceClassName
	@Resource(name = "serviceClassName")
	private String name;
}
