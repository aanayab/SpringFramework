package com.truper.spring.orm.practica27.test.dao.hibernate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.orm.practica27.dao.api.ICustomerDAO;
import com.truper.spring.orm.practica27.dao.api.IUserDAO;
import com.truper.spring.orm.practica27.domain.entities.Customer;
import com.truper.spring.orm.practica27.domain.entities.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practica27/orm-hibernate5-application-context.xml")
@Transactional
@Rollback(false)
@DirtiesContext
@ActiveProfiles("h2-in-memory") // h2-in-memory, h2-local, mysql
public class CustomerDAOTest {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private ICustomerDAO customerDAO;

	@Before
	public void setUp() {
		Assert.assertNotNull(userDAO);
		Assert.assertNotNull(customerDAO);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createCustomerTest() {
		log.info("createCustomerTest -------------------");

		User newUser = User.builder().username("laura123").password("123123").build();

		Customer newCustomer = Customer.builder().name("Laura").lastName("Montes").user(newUser).build();

		newUser.setCustomer(newCustomer);

		customerDAO.insert(newCustomer);

		log.info("newCustomer : {} {}", newCustomer, System.identityHashCode(newCustomer));

		customerDAO.detach(newCustomer);

		log.info("newCustomer (detached) : {} {}", newCustomer, System.identityHashCode(newCustomer));

		Customer customer = customerDAO.findById(newCustomer.getId());

		Assert.assertEquals(customer, newCustomer);

		log.info("customer : {} {}", customer, System.identityHashCode(customer));

		User user = userDAO.findById(customer.getUser().getId());

		Assert.assertEquals(user, customer.getUser());

		log.info("user : {} {}", user, System.identityHashCode(user));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateCustomerTest() {
		log.info("updateCustomerTest -------------------");

		User newUser = User.builder().username("laura123").password("123123").build();

		Customer newCustomer = Customer.builder().name("Laura").lastName("Montes").user(newUser).build();

		newUser.setCustomer(newCustomer);

		customerDAO.insert(newCustomer);

		log.info("newCustomer : {} {}", newCustomer, System.identityHashCode(newCustomer));

		customerDAO.detach(newCustomer);

		log.info("newCustomer (detached) : {} {}", newCustomer, System.identityHashCode(newCustomer));

		Customer customer = customerDAO.findById(newCustomer.getId());

		Assert.assertEquals(customer, newCustomer);

		log.info("customer : {} {}", customer, System.identityHashCode(customer));

		customer.setName("Laura Valeria");
		customer.setLastName("Manrique");

		customer.getUser().setUsername("lauravaleria123");
		customer.getUser().setPassword("123456789");

		customerDAO.update(customer);

		log.info("customer (modified) : {} {}", customer, System.identityHashCode(customer));

		Customer modifiedCustomer = customerDAO.findById(customer.getId());

		Assert.assertEquals(modifiedCustomer, customer);

		log.info("modifiedCustomer : {} {}", modifiedCustomer, System.identityHashCode(modifiedCustomer));

		User user = userDAO.findById(modifiedCustomer.getUser().getId());

		Assert.assertEquals(user, modifiedCustomer.getUser());

		log.info("user : {} {}", user, System.identityHashCode(user));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void deleteCustomerTest() {
		log.info("deleteCustomerTest -------------------");

		User newUser = User.builder().username("laura123").password("123123").build();

		Customer newCustomer = Customer.builder().name("Laura").lastName("Montes").user(newUser).build();

		newUser.setCustomer(newCustomer);

		customerDAO.insert(newCustomer);

		log.info("newCustomer : {} {}", newCustomer, System.identityHashCode(newCustomer));

		customerDAO.detach(newCustomer);

		log.info("newCustomer (detached) : {} {}", newCustomer, System.identityHashCode(newCustomer));

		Customer customer = customerDAO.findById(newCustomer.getId());

		Assert.assertEquals(customer, newCustomer);

		log.info("customer : {} {}", customer, System.identityHashCode(customer));

		customerDAO.delete(customer);

		log.info("customer (deleted) : {} {}", customer, System.identityHashCode(customer));

		Customer deletedCustomer = customerDAO.findById(customer.getId());

		Assert.assertNull(deletedCustomer);

		log.info("deletedCustomer : {} {}", deletedCustomer, System.identityHashCode(deletedCustomer));

		User user = userDAO.findById(customer.getUser().getId());

		Assert.assertNull(user);

		log.info("user : {} {}", user, System.identityHashCode(user));
	}
}
