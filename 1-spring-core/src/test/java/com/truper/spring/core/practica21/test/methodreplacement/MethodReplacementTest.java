package com.truper.spring.core.practica21.test.methodreplacement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.core.practica21.methodreplacement.bean.SportCar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = MethodReplacementTest.location)
public class MethodReplacementTest {

	public static final String location = "classpath:/spring/practica21/method-replacement-context.xml";

	@Autowired
	private SportCar sportCar;

	@Before
	public void setUp() {
		Assert.assertNotNull(sportCar);
	}

	@Test
	public void methodReplacerTest() {

		log.info("methodReplacerTest -------------------");

		sportCar.start();

		int mills = sportCar.run();

		Assert.assertEquals(200, mills);
	}

}
