package com.truper.spring.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truper.spring.rootbeans.BeanComponent;

@Controller
class IndexController {

	@Autowired
	private BeanComponent beanComponent;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showIndexPage(Model model) {

		model.addAttribute("message", "Hello World - Spring MVC (no web.xml, spring JavaConfig approach) ("
				+ beanComponent.sayHello("Ivan") + ")");
		return "index";
	}

	@GetMapping(value = "/message")
	public @ResponseBody String showIndexMessage() {

		return "Hello World - Spring MVC (no web.xml, spring JavaConfig approach) (" + beanComponent.sayHello("Ivan")
				+ ")";
	}

}