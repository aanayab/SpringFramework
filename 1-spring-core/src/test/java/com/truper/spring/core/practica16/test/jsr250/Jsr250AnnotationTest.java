package com.truper.spring.core.practica16.test.jsr250;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica16.jsr250.bean.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jsr250AnnotationTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica16/annotations-jsr250-application-context.xml");
	}

	@AfterClass
	public static void afterClass() {
		applicationContext.close();
	}

	@Test
	public void jsr250AnnotationTest() {

		log.info("jsr250AnnotationTest -------------------");

		// Implementar
		Student student = applicationContext.getBean(Student.class);

		Assert.assertNotNull(student);
		Assert.assertNotNull(student.getSubject());
		Assert.assertNotNull(student.getAlgebra());
		
		Assert.assertEquals("Ivan Garcia", student.getName());
		Assert.assertEquals("Spanish", student.getSubject().getName());
		Assert.assertEquals("10", student.getSubject().getCredits());
		Assert.assertEquals("Mathematics", student.getAlgebra().getName());
		Assert.assertEquals("15", student.getAlgebra().getCredits());

		log.info("student: {}", student);
	}

}
