package com.truper.spring.rootbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truper.spring.web.mvc.service.AddService;

import lombok.Data;

@Component
@Data
public class BeanComponent {

	@Autowired(required = false)
	private AddService webService;

	public String sayHello(String name) {
		return "Hello " + name + " !";
	}

}
