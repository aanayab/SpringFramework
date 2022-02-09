package com.truper.spring.core.practicaA.test.lifecycle;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practicaA.lifecycle.bean.Automovil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LifecycleTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		String ruta = "classpath:spring/practicaA/lifecycle-application-context.xml";
		
		applicationContext = new ClassPathXmlApplicationContext();
		applicationContext.setConfigLocation(ruta);
		applicationContext.refresh();
	}

	@AfterClass
	public static void afterClass() {
		applicationContext.registerShutdownHook();
	}

	@Test
	public void lifecycleTest() {

		log.info("lifecycleTest -------------------");

		Automovil automovil = applicationContext.getBean(Automovil.class);

		log.info("automovil: {}", automovil);

		automovil.run();
	}

}
