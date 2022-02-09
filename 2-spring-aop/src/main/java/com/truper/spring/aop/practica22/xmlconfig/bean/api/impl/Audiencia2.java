package com.truper.spring.aop.practica22.xmlconfig.bean.api.impl;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truper.spring.aop.practica22.xmlconfig.bean.api.IAudiencia2;
import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Define Bean Audiencia
@Component("audiencia2Bean")
public class Audiencia2 implements IAudiencia2 {

	// Inyecta IColorWriter
	@Autowired
	private IColorWriter colorWriter;

	@Override
	public void gritaGol(JoinPoint jp, boolean golazo) {
		
		if(!golazo)
			throw new RuntimeException();
		
		for(Object o : jp.getArgs()) {
			System.out.println(o.getClass().getSimpleName() +" "+o);
		}

		String sera = golazo ? "Se que sera gol..." : "No sera gol.";
		print(colorWriter.getColoredMessage(Color.YELLOW, "[Aspecto Audiencia 2] " 
					+ sera + " es gol? " + golazo));
	}

	private void print(String mensaje) {
		log.info("{}", mensaje);
	}

}
