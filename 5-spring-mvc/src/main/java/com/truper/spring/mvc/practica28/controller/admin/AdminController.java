package com.truper.spring.mvc.practica28.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truper.spring.mvc.practica28.rootbeans.BeanComponent;

// Definir controllr
@Controller
// Anotar request mapping "/admin"
@RequestMapping("/admin")
class AdminController {

	// Inyectar required false
	@Autowired
	private BeanComponent beanComponent;

	// anotar request mapping a "/" y a "" por GET
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String showIndexPage(final Model model, 
			@RequestParam(value = "name", required = false, defaultValue = "") String name) throws IOException {

		// Agregar mensaje "message" al modelo
		model.addAttribute("message", "Hello World Admin - Spring MVC (" + beanComponent.sayHello(name) + ")");
		
		return "admin"; // por default es un forward, retornamos el nombre de una vista
		//return "redirect:/url"; // para enviar un sendRedirect, retornamos redirect:Una Url de mi sistema
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@ResponseBody
	public String showMessage(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		return "Hello World Admin - Spring MVC (" + beanComponent.sayHello(name) + ")";
	}

}