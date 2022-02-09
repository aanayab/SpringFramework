package com.truper.spring.core.practica17.test.stereotypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.core.practica17.stereotypes.api.IComponentClass;
import com.truper.spring.core.practica17.stereotypes.api.IControllerClass;
import com.truper.spring.core.practica17.stereotypes.api.IRepositoryClass;
import com.truper.spring.core.practica17.stereotypes.api.IRestControllerClass;
import com.truper.spring.core.practica17.stereotypes.api.IServiceClass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Implementar run with spring-test
@RunWith(SpringJUnit4ClassRunner.class)
// cargar context configuration
@ContextConfiguration(locations = { "classpath:/spring/practica17/component-scan-stereotypes-application-context.xml" })
public class StereotypesBetterTest {

	// Inyectar todas las dependencias
	@Autowired
	private IRestControllerClass restController;

	@Autowired
	private IRestControllerClass restController2;

	@Autowired
	private IServiceClass service;

	@Autowired
	private IServiceClass service2;

	@Autowired
	private IControllerClass controller;

	@Autowired
	private IComponentClass component;

	@Autowired
	private IRepositoryClass repository;

	@Before
	public void beforeClass() {
		Assert.assertNotNull(restController);
		Assert.assertNotNull(component);
		Assert.assertNotNull(service);
		Assert.assertNotNull(controller);
		Assert.assertNotNull(repository);

		Assert.assertSame(restController, restController2);
		Assert.assertNotSame(service, service2);
	}

	@Test
	public void restControllerTest() {

		log.info("restControllerTest -------------------");

		String name = "My REST Controller Implementation";

		Assert.assertNotNull(restController);
		Assert.assertEquals(name, restController.getName());

		log.info("restController: {}", restController);
	}

	@Test
	public void controllerTest() {

		log.info("controllerTest -------------------");

		String name = "My Controller Implementation";

		Assert.assertNotNull(controller);
		Assert.assertEquals(name, controller.getName());

		log.info("controller: {}", controller);
	}

	@Test
	public void componentTest() {

		log.info("componentTest -------------------");

		String name = "My Component Implementation";

		Assert.assertNotNull(component);
		Assert.assertEquals(name, component.getName());

		log.info("component: {}", component);
	}

	@Test
	public void serviceTest() {

		log.info("serviceTest -------------------");

		String name = "My Service Implementation";

		Assert.assertNotNull(service);
		Assert.assertEquals(name, service.getName());

		log.info("service: {}", service);
	}

	@Test
	public void repositoryTest() {

		log.info("repositoryTest -------------------");

		String name = "My Repository Implementation";

		Assert.assertNotNull(repository);
		Assert.assertEquals(name, repository.getName());

		log.info("repository: {}", repository);
	}

}
