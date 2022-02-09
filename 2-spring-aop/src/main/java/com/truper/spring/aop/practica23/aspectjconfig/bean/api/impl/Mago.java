package com.truper.spring.aop.practica23.aspectjconfig.bean.api.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truper.spring.aop.practica23.aspectjconfig.bean.api.IAdivinador;
import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Define Bean
@Component
// Define Bean como Aspecto
@Aspect
public class Mago implements IAdivinador {

	// Inyecta Dependencia
	@Autowired
	private IColorWriter colorWriter;

	// Define pointcut que intercepte metodo IVoluntario.pensarxxx y que cache
	// el argumento
	@Pointcut("execution(void com.truper..IVoluntario.pensar*(String)) && args(pensamiento)")
	private void cuandoUnVoluntarioPiensaEnAlgo(String pensamiento) {
	}

	// Define pointcut que intercepte metodo IVoluntario.getPxxx y que cache el
	// argumento
	@Pointcut("execution(String com.truper..IVoluntario.getPensamiento(boolean)) && args(tramposo)")
	private void cuandoUnVoluntarioQuiereHAcerTrampa(boolean tramposo) {
	}

	@Override
	// Define advice Before para interceptar el pensamiento "antes de que el
	// voluntario piense en algo"
	@Before("cuandoUnVoluntarioPiensaEnAlgo(pensamiento)")
	public void interceptarPensamiento(JoinPoint jp, String pensamiento) {
		print(colorWriter.getColoredMessage(Color.YELLOW,
				"[Mago] El voluntario se prepara para pensar en algo... [Pensara en: "
						+ pensamiento + "]"));
	}

	// Define advice Around para envolver la llamada al metodo getPensamiento.
	// El metodo evalua si el voluntairo hara trampa o no.
	@Around("cuandoUnVoluntarioQuiereHAcerTrampa(hizoTrampa)")
	public Object hacerMagia(ProceedingJoinPoint pjp, boolean hizoTrampa)
			throws Throwable {

		if (hizoTrampa)
			return "No me he bañado en 5 dias";

		Object o = pjp.proceed();
		return o;
	}

	private void print(String mensaje) {
		log.info("{}", mensaje);
	}

}
