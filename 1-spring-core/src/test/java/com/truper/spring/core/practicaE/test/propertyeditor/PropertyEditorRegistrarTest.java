package com.truper.spring.core.practicaE.test.propertyeditor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.core.practicaE.propertyeditor.bean.CreditCard;
import com.truper.spring.core.practicaE.propertyeditor.bean.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = PropertyEditorRegistrarTest.location)
public class PropertyEditorRegistrarTest {

	public static final String location = "classpath:/spring/practicaE/property-editor-registrar-application-context.xml";

	@Value("1234-5698-7654-3210")
	private CreditCard creditCard;

	@Autowired
	private Customer customer;

	@Autowired
	private ObjectFactory<Customer> customerFactory;

	@Before
	public void setUp() {
		Assert.assertNotNull(creditCard);
		Assert.assertNotNull(customer);

		log.info("customer from object factory: {}", customerFactory.getObject());
	}

	@Test
	public void propertyEditorTest() {

		log.info("propertyEditorTest -------------------");

		log.info("credit card: {}", creditCard);

		log.info("customer: {}", customer);

		Assert.assertEquals(123456, creditCard.getBankIdNo(), 0);
		Assert.assertEquals(987654321, creditCard.getAccountNo(), 0);
		Assert.assertEquals(0, creditCard.getCheckCode(), 0);
		Assert.assertEquals("1234-5698-7654-3210", creditCard.getRawCardNumber());

		Assert.assertEquals(222222, customer.getCreditCard().getBankIdNo(), 0);
		Assert.assertEquals(333333333, customer.getCreditCard().getAccountNo(), 0);
		Assert.assertEquals(4, customer.getCreditCard().getCheckCode(), 0);
		Assert.assertEquals("2222-2233-3333-3334", customer.getCreditCard().getRawCardNumber());
	}
}