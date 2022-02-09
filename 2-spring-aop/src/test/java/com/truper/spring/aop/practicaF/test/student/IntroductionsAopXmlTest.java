package com.truper.spring.aop.practicaF.test.student;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.aop.practicaF.introductions.bean.Student;
import com.truper.spring.aop.practicaF.introductions.bean.StudentAdditionalDetails;
import com.truper.spring.aop.practicaF.introductions.bean.api.IStudent;
import com.truper.spring.aop.practicaF.introductions.bean.api.IStudentAdditionalDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practicaF/introductions-application-context.xml")
public class IntroductionsAopXmlTest {

	@Autowired
	private IStudent student;

	@Autowired
	private IStudentAdditionalDetails studentAdditionalDetails;

	@Before
	public void setUp() {
		Assert.assertNotNull(student);
		Assert.assertNotNull(studentAdditionalDetails);
	}

	@Test
	public void introductionsAopXMLTest() {

		log.info("introductionsAopXMLTest -------------------");

		student.getInfo();

		((IStudentAdditionalDetails) student).showAdditionalDetails();

		log.info("---");

		Assert.assertTrue(student instanceof IStudent);
		Assert.assertTrue(student instanceof Student);

		Assert.assertTrue(student instanceof IStudentAdditionalDetails);
		Assert.assertTrue(!(student instanceof StudentAdditionalDetails)); // efectivamente student no es un
																			// "StudentAdditionalDetails"

		System.out.println(student.getClass().getSimpleName());
	}

}
