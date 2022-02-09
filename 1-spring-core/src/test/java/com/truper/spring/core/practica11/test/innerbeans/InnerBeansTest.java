package com.truper.spring.core.practica11.test.innerbeans;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica11.innerbeans.bean.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InnerBeansTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@Before
	public void beforeTest() {
		applicationContext = new ClassPathXmlApplicationContext(
				"spring/practica11/inner-beans-application-context.xml");
	}

	@After
	public void afterTest() {
		applicationContext.close();
	}

	@Test
	public void innerBeansTest() {

		log.info("innerBeansTest -------------------");

		Person person = applicationContext.getBean(Person.class);

		Assert.assertNotNull(person);
		Assert.assertNotNull(person.getName());
		Assert.assertEquals("Ivan", person.getName());
		Assert.assertNotNull(person.getAge());
		Assert.assertEquals(34, person.getAge());
		Assert.assertNotNull(person.getAddress());
		Assert.assertEquals("Uxmal", person.getAddress().getStreet());
		Assert.assertEquals("123", person.getAddress().getExternalNumber());
		Assert.assertEquals("s/n", person.getAddress().getInternalNumber());
		Assert.assertEquals("Paseos de Taxqueña", person.getAddress().getNeighbor());

		log.info("person: {}", person);
	}

	@Test
	public void getStreetNameBeanTest() {

		log.info("getStreetNameBeanTest -------------------");

		String streetNameBean = applicationContext.getBean("streetNameBean", String.class);

		Assert.assertNotNull(streetNameBean);
		Assert.assertEquals("Uxmal", streetNameBean);

		log.info("streetNameBean: {}", streetNameBean);
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void getNameBeanTest() {

		log.info("getNameBeanTest -------------------");

		applicationContext.getBean("nameBean", String.class);

		Assert.fail("Should have to fail at this line");
	}

}
