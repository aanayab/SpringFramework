package com.truper.spring.core.practica9.test.beandefinheritance.bean;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica9.beandefinitioninheritance.bean.ConnectionDataBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanDefinitionInheritanceTest {

	@Test
	public void beanDefinitionInheritanceTest1() {

		log.info("beanDefinitionInheritanceTest1 -------------------");

		// Implementar
		String ruta = "spring/practica9/bean-def-inheritance-application-context.xml";
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(ruta);

		ConnectionDataBase conexionProduccion = applicationContext.getBean("connectionProdBean",
				ConnectionDataBase.class);

		Assert.assertNotNull(conexionProduccion);
		Assert.assertEquals("produccionDB001", conexionProduccion.getDatabase());
		Assert.assertEquals("pepitoRoot_Admin2", conexionProduccion.getUser());
		Assert.assertEquals("WEF#WDfsd.789871234EWR", conexionProduccion.getPassword());
		Assert.assertFalse(conexionProduccion.isDebugMode());

		conexionProduccion.showInfo();

		ConnectionDataBase conexionPruebas = applicationContext.getBean("connectionTestBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionPruebas);
		Assert.assertEquals("testDB101", conexionPruebas.getDatabase());
		Assert.assertEquals("pepitoRoot_Admin2", conexionPruebas.getUser());
		Assert.assertEquals("WEF#WDfsd.789871234EWR", conexionPruebas.getPassword());
		Assert.assertTrue(conexionPruebas.isDebugMode());

		conexionPruebas.showInfo();

		applicationContext.close();
	}

	@Test
	public void beanDefinitionInheritanceTest2() {

		log.info("beanDefinitionInheritanceTest2 -------------------");

		// Implementar
		String ruta = "spring/practica9/bean-def-inheritance-application-context2.xml";
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(ruta);

		ConnectionDataBase conexionProduccion = applicationContext.getBean("connectionProdBean",
				ConnectionDataBase.class);

		Assert.assertNotNull(conexionProduccion);
		Assert.assertEquals("produccionDB001", conexionProduccion.getDatabase());
		Assert.assertEquals("pepitoRoot_Admin2", conexionProduccion.getUser());
		Assert.assertEquals("WEF#WDfsd.789871234EWR", conexionProduccion.getPassword());
		Assert.assertFalse(conexionProduccion.isDebugMode());

		conexionProduccion.showInfo();

		ConnectionDataBase conexionPruebas = applicationContext.getBean("connectionTestBean", ConnectionDataBase.class);

		Assert.assertNotNull(conexionPruebas);
		Assert.assertEquals("testDB101", conexionPruebas.getDatabase());
		Assert.assertEquals("pepitoRoot_Admin2", conexionPruebas.getUser());
		Assert.assertEquals("WEF#WDfsd.789871234EWR", conexionPruebas.getPassword());
		Assert.assertTrue(conexionPruebas.isDebugMode());

		conexionPruebas.showInfo();

		applicationContext.close();
	}

}
