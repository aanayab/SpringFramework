package com.truper.spring.core.practica17.stereotypes.api.restcontrollers.impl;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.truper.spring.core.practica17.stereotypes.api.IRestControllerClass;

import lombok.Data;

@Data
// Anotar rest controlador, implementar Interface IRestControllerClass
@RestController
public class MyRestController implements IRestControllerClass {

	// Inyectar con "resource" bean restControllerClassName
	@Resource(name = "restControllerClassName")
	private String name;
}
