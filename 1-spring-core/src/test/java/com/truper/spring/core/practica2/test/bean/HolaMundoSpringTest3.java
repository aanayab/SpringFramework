package com.truper.spring.core.practica2.test.bean;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.truper.spring.core.practica2.bean.HolaMundo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HolaMundoSpringTest3 {

	private static ApplicationContext context;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext Antes de ejecutar la clase de test
		String ruta = "src/main/resources/spring/practica2/beans.xml"; // C: (windows)   //(Unix)
		context = new ClassPathXmlApplicationContext(ruta); // default classpath:
		//context = new FileSystemXmlApplicationContext(ruta); // default file:
	}

	@AfterClass
	public static void afterClass() {
		// Cerrar ApplicationContext
		((ConfigurableApplicationContext) context).close();
	}

	@Test
	public void holaMundoSpringTest2() {
		log.info("holaMundoSpringTest2 -------------------------");

		// Implementar IoC con ApplicationContext
		
		HolaMundo hola1 = context.getBean("hola1", HolaMundo.class);
		HolaMundo hola11 = context.getBean("hola1", HolaMundo.class);
		
		Assert.assertSame(hola1, hola11);
		Assert.assertTrue(hola1 == hola11);
		
		HolaMundo hola2 = context.getBean("hola2", HolaMundo.class);
		HolaMundo hola22 = context.getBean("hola2", HolaMundo.class);
		
		Assert.assertSame(hola2, hola22);
		Assert.assertTrue(hola2 == hola22);

		Assert.assertNotNull(hola2);
		Assert.assertNotNull(hola2.getMensaje());
		Assert.assertEquals("Hola mundo", hola2.getMensaje());
	}
	
}
