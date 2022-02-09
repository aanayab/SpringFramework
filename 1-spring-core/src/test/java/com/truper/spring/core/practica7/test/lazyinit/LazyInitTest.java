package com.truper.spring.core.practica7.test.lazyinit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica7.lazyinit.bean.Car;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LazyInitTest {

	private static ConfigurableApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "classpath:/spring/practica7/lazy-init-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@AfterClass
	public static void afterClass() {
		// Cerrar ApplicationContext
		applicationContext.close();
	}

	@Test
	public void lazyInitTest() {

		log.info("lazyInitTest -------------------");

		// Implementar
		for(int i = 0; i<2; i++) {
			Car car = applicationContext.getBean(Car.class);
			Assert.assertNotNull(car);
		}
	}

}
