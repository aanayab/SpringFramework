package com.truper.spring.core.practica17.stereotypes.api.controllers.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.truper.spring.core.practica17.stereotypes.api.IControllerClass;

import lombok.Data;

@Data
// Anotar controlador, implementar Interface IControllerClass
@Controller
public class MyController implements IControllerClass {

	// Inyectar con "resource" bean controllerClassName
	@Resource(name = "controllerClassName")
	private String name;
}
