package com.truper.spring.core.practica20.test.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.core.practica20.resources.bean.MyResourceLoaderAware;
import com.truper.spring.core.practica20.test.resources.utils.ResourcesTestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//Implementar run with spring-test
@RunWith(SpringJUnit4ClassRunner.class)
//cargar context configuration
@ContextConfiguration(locations = ResourceLoaderAwareTest.location)
public class ResourceLoaderAwareTest {

	public static final String location = "classpath:/spring/practica20/resources-application-context.xml";

	// Inyectar
	@Autowired
	private MyResourceLoaderAware beanResourceLoaderAware;

	@Test
	public void loadTextFileClasspathXmlApplicationContextTest() {

		log.info("loadTextFileClasspathXmlApplicationContextTest -------------------");

		// loadTextFile
		Resource resource = beanResourceLoaderAware.getResourceLoader()
				.getResource("file:/Users/xvhx/truper-resources/my-text-file.txt");
		ResourcesTestUtils.loadTextFile(resource);
	}

	@Test
	public void loadPropertiesFileClasspathXmlApplicationContextTest() {

		log.info("loadPropertiesFileClasspathXmlApplicationContextTest -------------------");

		// loadPropertiesFile
		Resource resource = beanResourceLoaderAware.getResourceLoader()
				.getResource("classpath:spring/practica20/my-properties.properties");
		ResourcesTestUtils.loadPropertiesFile(resource);
	}

	@Test
	public void loadUrlFileClasspathXmlApplicationContextTest() {

		log.info("loadUrlFileClasspathXmlApplicationContextTest -------------------");

		// loadURLFile
		Resource resource = beanResourceLoaderAware.getResourceLoader()
				.getResource("https://spring.io/");
		ResourcesTestUtils.loadURLFile(resource);
	}

	@Test
	public void loadAndCopyImageFileClasspathXmlApplicationContextTest() {

		log.info("loadAndCopyImageFileClasspathXmlApplicationContextTest -------------------");

		// loadAndCopyImage
		Resource resource = beanResourceLoaderAware.getResourceLoader()
				.getResource("classpath:/spring/practica20/logo.png");
		ResourcesTestUtils.loadAndCopyImage(resource,
				"src/main/resources/spring/practica20/copy-resource-loader-aware/");
	}
}
