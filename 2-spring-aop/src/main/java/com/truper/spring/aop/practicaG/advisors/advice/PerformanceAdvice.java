package com.truper.spring.aop.practicaG.advisors.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Implementa Advice MethodBeforeAdvice y AfterReturningAdvice
public class PerformanceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private ThreadLocal<Long> finishTime = new ThreadLocal<Long>();

	@Autowired
	private IColorWriter colorWriter;

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		startTime.set(System.currentTimeMillis());

		log.info("{}", colorWriter.getColoredMessage(Color.CYAN,
				"Executing method " + method.getName() + " on object " + target.getClass().getName()));
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		finishTime.set(System.currentTimeMillis());
		double totalDuration = finishTime.get() - startTime.get();

		log.info("{}", colorWriter.getColoredMessage(Color.CYAN, "Finished executing method " + method.getName()
				+ " on object " + target.getClass().getName() + " in " + totalDuration / 1000 + " seconds."));
	}

}
