package com.truper.spring.mvc.practica28.rootbeans.service.api.impl;

import org.springframework.stereotype.Component;

import com.truper.spring.mvc.practica28.rootbeans.service.api.IAddService;

import lombok.Data;

@Component
@Data
public class AddServiceImpl implements IAddService {

	public Double add(Double... numbers) {

		Double z = 0D;

		if (numbers != null)
			for (Double n : numbers)
				z += n;

		return z;

	}
}
