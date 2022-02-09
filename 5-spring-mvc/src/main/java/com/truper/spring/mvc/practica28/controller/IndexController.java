package com.truper.spring.mvc.practica28.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truper.spring.mvc.practica28.rootbeans.BeanComponent;

// Anotar controller
@Controller
public class IndexController {

	// Inyectar required false
	@Autowired
	private BeanComponent beanComponent;

	// anotar request mapping a "/" y a "" por GET
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showIndexPage(final Model model,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		// Agregar mensaje "message" al modelo
		model.addAttribute("message", "Hello World - Spring MVC (" + beanComponent.sayHello(name) + ")");

		return "index";
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseBody
	public String showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		return "Hello World - Spring MVC (" + beanComponent.sayHello(name) + ")";
	}

}