package com.truper.spring.core.practica10.test.beanpostprocessors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica10.beanpostprocessors.bean.Worker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanPostProcessorsTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@Before
	public void beforeTest() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica10/bean-post-processors-application-context.xml");
	}

	@After
	public void afterTest() {
		applicationContext.close();
	}

	@Test
	public void beanPostProcessorsTest() {

		log.info("beanPostProcessorsTest -------------------");

		Worker worker = applicationContext.getBean(Worker.class);

		// worker no va a ser de Tipo Worker (osea si) pero mas bien es de tipo
		// WorkerProxy...
		worker.setName("Tannia");

		log.info("worker: {}", worker);

		Assert.assertNotNull(worker);
		Assert.assertEquals("Tannia", worker.getName());
		Assert.assertEquals(31, worker.getAge());

		worker.showInfo();

		// Assert.fail("El Worker debe morir para este momento");
	}

}
