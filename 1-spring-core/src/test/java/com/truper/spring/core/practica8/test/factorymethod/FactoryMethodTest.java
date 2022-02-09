package com.truper.spring.core.practica8.test.factorymethod;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica8.factorymethod.bean.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryMethodTest {

	private static ConfigurableApplicationContext applicationContext;

	@Before
	public void before() {
		// Instanciar ApplicationContext
		String ruta = "classpath:/spring/practica8/factory-method-application-context.xml";
		applicationContext = new AnnotationConfigApplicationContext();
	}

	@After
	public void after() {
		// Cerrar ApplicationContext
		applicationContext.close();
	}

	@Test
	public void factoryMethodTest1() {

		log.info("factoryMethodTest1 -------------------");

		// Implementar
		Student student1 = applicationContext.getBean("student1", Student.class);

		Assert.assertNotNull(student1);
		Assert.assertNotNull(student1.getName());
		Assert.assertEquals("Paula Sofia", student1.getName());
		Assert.assertNotNull(student1.getSubject());
		Assert.assertNotNull(student1.getSubject().getName());
		Assert.assertEquals("Programacion III", student1.getSubject().getName());
		Assert.assertNotNull(student1.getSubject().getTeacher());
		Assert.assertNotNull(student1.getSubject().getTeacher().getName());
		Assert.assertEquals("Ivan", student1.getSubject().getTeacher().getName());

		log.info("student1: {}", student1);

	}

	@Test
	public void factoryMethodTest2() {

		log.info("factoryMethodTest2 -------------------");

		// Implementar
		Student student2 = applicationContext.getBean("student2", Student.class);

		Assert.assertNotNull(student2);
		Assert.assertNotNull(student2.getName());
		Assert.assertEquals("Paula Sofia", student2.getName());
		Assert.assertNotNull(student2.getSubject());
		Assert.assertNotNull(student2.getSubject().getName());
		Assert.assertEquals("Programacion III", student2.getSubject().getName());
		Assert.assertNotNull(student2.getSubject().getTeacher());
		Assert.assertNotNull(student2.getSubject().getTeacher().getName());
		Assert.assertEquals("Ivan", student2.getSubject().getTeacher().getName());

		log.info("student2: {}", student2);
	}

}
