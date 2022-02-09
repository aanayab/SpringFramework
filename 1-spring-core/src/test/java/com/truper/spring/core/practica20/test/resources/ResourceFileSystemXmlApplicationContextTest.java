package com.truper.spring.core.practica20.test.resources;

import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.truper.spring.core.practica20.test.resources.utils.ResourcesTestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceFileSystemXmlApplicationContextTest {

	private static ApplicationContext applicationContext = new FileSystemXmlApplicationContext();

	@AfterClass
	public static void releaseApplicationContext() {
		((AbstractApplicationContext) applicationContext).close();
	}

	@Test
	public void loadTextFileFileSystemXmlApplicationContextTest() {

		log.info("loadTextFileFileSystemXmlApplicationContextTest -------------------");

		// load resource c:/truper-resources/my-text-file.txt
		Resource resource = applicationContext.getResource("//Users/xvhx/truper-resources/my-text-file.txt");
		ResourcesTestUtils.loadTextFile(resource);
	}

	@Test
	public void loadPropertiesFileFileSystemXmlApplicationContextTest() {

		log.info("loadPropertiesFileFileSystemXmlApplicationContextTest -------------------");

		// load resource classpath:spring/practica20/my-properties.properties
		// load resource src/main/resources/spring/practica20/my-properties.properties

		// loadPropertiesFile from resource
		Resource resource = applicationContext
				.getResource("src/main/resources/spring/practica20/my-properties.properties");
		ResourcesTestUtils.loadPropertiesFile(resource);
	}

	@Test
	public void loadUrlFileFileSystemXmlApplicationContextTest() {

		log.info("loadUrlFileFileSystemXmlApplicationContextTest -------------------");

		// load resource http://spring.io/

		// loadURLFile from resource
		Resource resource = applicationContext.getResource("https://spring.io/");
		ResourcesTestUtils.loadURLFile(resource);
	}

	@Test
	public void loadAndCopyImageFileFileSystemXmlApplicationContextTest() {

		log.info("loadAndCopyImageFileFileSystemXmlApplicationContextTest -------------------");

		// load resource src/main/resources/spring/practica20/logo.png

		Resource resource = applicationContext.getResource("src/main/resources/spring/practica20/logo.png");
		ResourcesTestUtils.loadAndCopyImage(resource, "src/main/resources/spring/practica20/copy-fileSystem/");
	}
}
