package com.truper.spring.core.practica15.test.autowired.methods;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica15.autowired.constructor.bean.Engineer;
import com.truper.spring.core.practica15.autowired.methods.bean.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredMethodsAnnotationTest {

	private static ConfigurableApplicationContext ctx;

	@Before
	public void beforeClass() {
		String ruta = "spring/practica15/annotations-autowired-methods-application-context.xml";
		ctx = new ClassPathXmlApplicationContext(ruta);
	}

	@After
	public void afterClass() {
		ctx.close();
	}

	@Test
	public void autowiredMethodsAnnotationTest() {

		log.info("autowiredMethodsAnnotationTest -------------------");

		// Implementar
		Student student = ctx.getBean(Student.class);

		Assert.assertNotNull(student);
		Assert.assertNotNull(student.getName());
		Assert.assertEquals("Lara Croft", student.getName());
		Assert.assertNull(student.getDni());
		Assert.assertNotNull(student.getStudentBook());
		Assert.assertNotNull(student.getPropellingPencil());

		log.info("student: {}", student);
	}

}
