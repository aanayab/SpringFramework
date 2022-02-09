package com.truper.spring.aop.practica24.bank.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.truper.spring.aop.practica24.bank.app.model.Account;
import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//Define el Bean como Aspecto
@Aspect
@Component("webAccountLoggingAspect")
@Slf4j
public class WebAccountLoggingAspect implements Ordered {

	private @Getter int order = 1;

	@Autowired
	private IColorWriter colorWriter;

	// Define Advice Before que intercepte webLayer() y cache los argumentos
	// (la cuenta debe especificar como nombre de parametro "cuenta")
	@Before(value = "com.truper.spring.aop.practica24.bank.aop.PointcutDefinition.webLayer() && args(account,..)", 
			argNames = "account")
	public void beforeAccountMethodExecutionAccount(JoinPoint jp, Account acc) {

		log.info("{}", colorWriter.getColoredMessage(Color.YELLOW,
				String.format("Logging Web View Account access. Account: %s", acc.getAccountNumber())));

	}

	// Define Advice Before que intercepte webLayer() y cache los argumentos
	// (el customer Id debe especificar como nombre de parametro "id")
	@Before(value = "com.truper.spring.aop.practica24.bank.aop.PointcutDefinition.webLayer() && args(id,..)", 
			argNames = "id")
	public void beforeAccountMethodExecutionLong(JoinPoint jp, Long numberId) {

		log.info("{}", colorWriter.getColoredMessage(Color.YELLOW,
				String.format("Logging Web View Account access. Customer Id: %s", numberId)));

	}
}
