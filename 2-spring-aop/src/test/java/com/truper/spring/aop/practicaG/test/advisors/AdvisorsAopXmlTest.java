package com.truper.spring.aop.practicaG.test.advisors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.aop.practicaG.advisors.bean.BusinessService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practicaG/advisors-application-context.xml")
public class AdvisorsAopXmlTest {

	@Autowired
	private BusinessService businesService;

	@Before
	public void setUp() {
		Assert.assertNotNull(businesService);
	}

	@Test
	public void advisorsAopXMLTest() {

		log.info("advisorsAopXMLTest -------------------");

		businesService.execute();
	}

}
