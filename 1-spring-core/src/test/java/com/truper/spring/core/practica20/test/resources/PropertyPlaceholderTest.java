package com.truper.spring.core.practica20.test.resources;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.truper.spring.core.practica20.resources.bean.FavouriteRockBands;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Implementar run with spring-test
@RunWith(SpringRunner.class)
// cargar context configuration
@ContextConfiguration(locations = PropertyPlaceholderTest.location)
public class PropertyPlaceholderTest {

	public static final String location = "classpath:/spring/practica20/resources-application-context.xml";
	
	// Inyectar
	// @Value("#{ favouriteRockBands }") // Expression de SpEL
	// @Value("#{ @favouriteRockBands }")
	@Value("#{ @'favouriteRockBands' }")
	private FavouriteRockBands rockbands;

	// Inyectar property service.name
	@Value("${service.name}")
	private String serviceName;

	// Inyectar property service.description
	@Value("${service.description}")
	private String serviceDescription;

	// Inyectar property datasource.name
	@Value("${datasource.name}")
	private String datasourceName;

	// Inyectar property datasource.description
	@Value("${datasource.description}")
	private String datasourceDescription;

	// Inyectar property app.name
	@Value("${app.name}")
	private String appName;

	// Inyectar property app.description
	@Value("${app.description}")
	private String appDescription;

	@Value("${xyz.abc:xyz no exists}")
	private String noExists;
	
	@Value("#{ propertiesBean.getProperty('programmer.name') }") // Expression SpEL
	private String programmerName;

	@Test
	public void favouriteRockBandsTest() {

		log.info("favouriteRockBandsTest -------------------");

		Assert.assertNotNull(rockbands);

		Assert.assertEquals("Guns n' Roses", rockbands.getFirstRockBand());
		Assert.assertEquals("AC/DC", rockbands.getSecondRockBand());

		log.info("rockbands: {}", rockbands);
	}

	@Test
	public void propertyPlaceholderTest() {

		log.info("propertyPlaceholderTest -------------------");

		Assert.assertEquals("MyApp", appName);
		Assert.assertEquals("App to manage users and admins", appDescription);

		Assert.assertEquals("MyService", serviceName);
		Assert.assertEquals("Service bean to provide some data", serviceDescription);

		Assert.assertEquals("MyDatasource", datasourceName);
		Assert.assertEquals("Datasource to manage data to some database", datasourceDescription);

		log.info("appName: {}", appName);
		log.info("appDescription: {}", appDescription);
		log.info("serviceName: {}", serviceName);
		log.info("serviceDescription: {}", serviceDescription);
		log.info("datasourceName: {}", datasourceName);
		log.info("datasourceDescription: {}", datasourceDescription);
	}

	@Test
	public void noExistsTest() {

		log.info("noExistsTest -------------------");

		Assert.assertEquals("xyz no exists", noExists);
		Assert.assertEquals("Ivan V G", programmerName);

		log.info("noExists: {}", noExists);
		log.info("programmerName: {}", programmerName);
	}

}
