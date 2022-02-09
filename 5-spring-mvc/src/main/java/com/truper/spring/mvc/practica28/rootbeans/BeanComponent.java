package com.truper.spring.mvc.practica28.rootbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truper.spring.mvc.practica28.controller.IndexController;
import com.truper.spring.mvc.practica28.rootbeans.service.api.IAddService;

import lombok.Data;

@Component
@Data
public class BeanComponent {

	@Autowired
	private IAddService addService;

	@Autowired(required = false)
	private IndexController indexController;

	public String sayHello(String name) {
		return "Hello " + name + " !";
	}

}
