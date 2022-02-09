package com.truper.spring.core.practica6.test.initdestroy;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica6.initdestroy.bean.ConnectionDataBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitDestroyTest {

	private static ConfigurableApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "classpath:/spring/practica6/init-destroy-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@AfterClass
	public static void afterClass() {
		// Cerrar ApplicationContext
		applicationContext.close();
	}

	@Test
	public void initDestroyTest() {

		log.info("initDestroyTest -------------------");

		// Implementar

		ConnectionDataBase con = applicationContext.getBean("bean2", ConnectionDataBase.class);

		Assert.assertNotNull(con);
		Assert.assertNotNull(con.getUser());
		Assert.assertNotNull(con.getPassword());
		Assert.assertNotNull(con.getDatabase());
	}

}
