package com.truper.spring.aop.practica24.bank.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//Define el Bean como Aspecto
@Aspect
@Component("profilingAspect")
@Slf4j
public class ProfilingAspect implements Ordered {

	private @Getter int order = 1;

	@Autowired
	private IColorWriter colorWriter;

	// Define Around ADvice que intercepte cualquier metodo del paquete
	// com.truper.spring.aop.practica24.bank..* y cache al menos el primer
	// argumento
	@Around("within(com.truper..practica24.bank..*) && @annotation(com.truper.spring.aop.practica24.bank.aop.StopWatchProfiler) && args(obj,..)")
	public Object beforeAccountMethodExecutionAccount(ProceedingJoinPoint pjp, Object obj) throws Throwable {

		// marca de tiempo inicio
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(pjp.toShortString());

		boolean isExceptionThrown = false;
		try {

			return pjp.proceed();

		} catch (Exception ex) {

			isExceptionThrown = true;

			throw ex;

		} finally {
			// marca de tiempo de fin
			stopWatch.stop();
			
			// imprimir la diferencia de marcas de tiempo
			TaskInfo task = stopWatch.getLastTaskInfo();
			
			String exception = isExceptionThrown ? "(Exception thrown)" : "";
			
			String message = String.format("%s: %s ms %s", 
					task.getTaskName(), task.getTimeMillis(), exception).trim();
			
			log.info("{}, object intercepted: {}", 
					colorWriter.getColoredMessage(Color.GREEN, message),
					colorWriter.getColoredMessage(Color.GREEN, obj.getClass().getSimpleName()));
		}
	}
}
