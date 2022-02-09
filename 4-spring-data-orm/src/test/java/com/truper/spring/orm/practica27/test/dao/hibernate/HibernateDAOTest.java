package com.truper.spring.orm.practica27.test.dao.hibernate;

import java.math.BigDecimal;
import java.util.List;

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

import com.truper.spring.core.utils.DateUtils;
import com.truper.spring.orm.practica27.dao.api.IAccountDAO;
import com.truper.spring.orm.practica27.dao.api.ICustomerDAO;
import com.truper.spring.orm.practica27.dao.api.IUserDAO;
import com.truper.spring.orm.practica27.domain.entities.Account;
import com.truper.spring.orm.practica27.domain.entities.Customer;
import com.truper.spring.orm.practica27.domain.entities.User;
import com.truper.spring.orm.practica27.domain.vo.CustomDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practica27/orm-hibernate5-application-context.xml")
@Transactional
@Rollback(false)
@DirtiesContext
@ActiveProfiles("h2-in-memory") // h2-in-memory, h2-local, mysql
public class HibernateDAOTest {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private ICustomerDAO customerDAO;

	@Autowired
	private IAccountDAO accountDAO;

	@Before
	public void setUp() {
		Assert.assertNotNull(userDAO);
		Assert.assertNotNull(customerDAO);
		Assert.assertNotNull(accountDAO);
	}

	@Test
	public void hibernateDAOTest() {
		log.info("hibernateDAOTest -------------------");

		Customer insertedCustomer = createUserCustomer();

		createAccounts(insertedCustomer);

		User user = userDAO.findById(1L);

		Customer customer = customerDAO.findById(1L);

		List<Account> accounts = accountDAO.findByCustomerId(customer.getId());

		Account account_2 = accountDAO.findById(2L);

		Assert.assertNotNull(user);
		Assert.assertNotNull(customer);
		Assert.assertNotNull(accounts);
		Assert.assertEquals(2, accounts.size());
		Assert.assertNotNull(account_2);

		Assert.assertEquals("xvanhalenx", user.getUsername());
		Assert.assertEquals("Ivan Venor", customer.getName());
		Assert.assertEquals(account_2, accounts.get(1));

		log.info("user: {}", user);
		log.info("user.customer: {}", user.getCustomer());
		log.info("customer: {}", customer);

		log.info("accounts: {}", accounts);
		log.info("account_2: {}", account_2);

	}

	private void createAccounts(Customer insertedCustomer) {
		Account account_0 = Account.builder().accountNumber("00112233445566")
				.createdDate(new CustomDate(DateUtils.parseFecha("28/02/2019").getTime()))
				.balance(new BigDecimal(125590.55)).customer(insertedCustomer).build();

		Account account_1 = Account.builder().accountNumber("00112233445577")
				.createdDate(new CustomDate(DateUtils.parseFecha("20/02/2019").getTime()))
				.balance(new BigDecimal(150000.55)).customer(insertedCustomer).build();

		accountDAO.insert(account_0);
		accountDAO.insert(account_1);
	}

	private Customer createUserCustomer() {
		User newUser = User.builder().username("xvanhalenx").password("123123").build();

		Customer newCustomer = Customer.builder().name("Ivan Venor").lastName("Garcia").user(newUser).build();

		newUser.setCustomer(newCustomer);

		customerDAO.insert(newCustomer);

		customerDAO.detach(newCustomer);

		return newCustomer;
	}
}
