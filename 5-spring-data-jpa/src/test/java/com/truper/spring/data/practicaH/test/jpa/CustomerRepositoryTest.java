package com.truper.spring.data.practicaH.test.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.data.practicaH.jpa._config.SpringDataJpaConfiguration;
import com.truper.spring.data.practicaH.jpa.entity.Customer;
import com.truper.spring.data.practicaH.jpa.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void createSimpleCustomer() {

		log.info("createSimpleCustomer test starts =======================================================");

		Customer newCustomer = new Customer();
		newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
		newCustomer.setFullName("Ivan Garcia");

		Customer savedCustomer = customerRepository.save(newCustomer); // almacena newCustomer

		Assert.assertThat(savedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(savedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(savedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Garcia"));
		Assert.assertThat(savedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1987, 06, 23)));
		Assert.assertThat(savedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(newCustomer)));

		log.info("saved customer " + savedCustomer);
		log.info("saved customer is same instance than new customer: " + (savedCustomer == newCustomer));

		Optional<Customer> reloadedOptionalCustomer = customerRepository.findById(savedCustomer.getId()); // busca por Id el savedCustomer

		Assert.assertThat(reloadedOptionalCustomer.isPresent(), CoreMatchers.equalTo(true));

		Customer reloadedCustomer = reloadedOptionalCustomer.get();

		Assert.assertThat(reloadedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(reloadedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(reloadedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Garcia"));
		Assert.assertThat(reloadedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1987, 06, 23)));
		
		System.out.println(System.identityHashCode(newCustomer));
		System.out.println(System.identityHashCode(reloadedCustomer));
		
		log.info("reloaded customer " + reloadedCustomer);
		log.info("reloaded customer is same instance than new customer: " + (reloadedCustomer == newCustomer));
		log.info("reloaded customer is same instance than saved customer: " + (reloadedCustomer == savedCustomer));

		log.info("createSimpleCustomer test ends =======================================================");
	}

	@Test
	public void updateSimpleCustomer() {

		log.info("updateSimpleCustomer test starts =======================================================");

		Customer newCustomer = new Customer();
		newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
		newCustomer.setFullName("Ivan Garcia");

		Customer savedCustomer = customerRepository.save(newCustomer); // almacena newCustomer

		Assert.assertThat(savedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(savedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(savedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Garcia"));
		Assert.assertThat(savedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1987, 06, 23)));
		Assert.assertThat(savedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(newCustomer)));

		log.info("saved customer " + savedCustomer);
		log.info("saved customer is same instance than new customer: " + (savedCustomer == newCustomer));

		// savedCustomer modificado
		savedCustomer.setFullName("Ivan Venor Garcia");
		savedCustomer.setBirthday(LocalDate.of(1990, 01, 04));

		Customer updatedCustomer = customerRepository.save(savedCustomer); // actualiza el savedCustomer

		Assert.assertThat(updatedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(updatedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(updatedCustomer.getId(), CoreMatchers.equalTo(newCustomer.getId()));
		Assert.assertThat(updatedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Venor Garcia"));
		Assert.assertThat(updatedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1990, 01, 04)));
		Assert.assertThat(updatedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(savedCustomer)));
		Assert.assertThat(updatedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(newCustomer)));

		log.info("updated customer " + updatedCustomer);
		log.info("updated customer is same instance than new customer: " + (updatedCustomer == newCustomer));
		log.info("updated customer is same instance than saved customer: " + (updatedCustomer == savedCustomer));

		Optional<Customer> reloadedOptionalCustomer = customerRepository.findById(updatedCustomer.getId()); // busca por Id el updatedCustomer 

		Assert.assertThat(reloadedOptionalCustomer.isPresent(), CoreMatchers.equalTo(true));

		Customer reloadedCustomer = reloadedOptionalCustomer.get();

		Assert.assertThat(reloadedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(reloadedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(reloadedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Venor Garcia"));
		Assert.assertThat(reloadedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1990, 01, 04)));
		
		log.info("reloaded customer " + reloadedCustomer);
		log.info("reloaded customer is same instance than new customer: " + (reloadedCustomer == newCustomer));
		log.info("reloaded customer is same instance than saved customer: " + (reloadedCustomer == savedCustomer));
		log.info("reloaded customer is same instance than updated customer: " + (reloadedCustomer == updatedCustomer));

		log.info("updateSimpleCustomer test ends =======================================================");
	}

	@Test
	public void deleteSimpleCustomer() {

		log.info("deleteSimpleCustomer test starts =======================================================");

		Customer newCustomer = new Customer();
		newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
		newCustomer.setFullName("Ivan Garcia");

		Customer savedCustomer = customerRepository.save(newCustomer); // almacena newCustomer

		Assert.assertThat(savedCustomer.getId(), CoreMatchers.notNullValue());
		Assert.assertThat(savedCustomer.getId(), CoreMatchers.equalTo(1L));
		Assert.assertThat(savedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Garcia"));
		Assert.assertThat(savedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1987, 06, 23)));
		Assert.assertThat(savedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(newCustomer)));

		log.info("saved customer " + savedCustomer);
		log.info("saved customer is same instance than new customer: " + (savedCustomer == newCustomer));

		// elimina savedCustomer
		customerRepository.delete(savedCustomer);

		Optional<Customer> reloadedOptionalCustomer = customerRepository.findById(savedCustomer.getId()); // busca por Id el savedCustomer

		Assert.assertThat(reloadedOptionalCustomer.isPresent(), CoreMatchers.equalTo(false));

		Customer reloadedCustomer = reloadedOptionalCustomer.orElse(null);

		Assert.assertThat(reloadedCustomer, CoreMatchers.nullValue());

		log.info("reloaded customer " + savedCustomer);

		log.info("deleteSimpleCustomer test ends =======================================================");
	}

	@Test
	public void listAllSimpleCustomer() {

		log.info("listAllSimpleCustomer test starts =======================================================");

		for (int i = 1; i <= 3; i++) {
			Customer newCustomer = new Customer();
			newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
			newCustomer.setFullName("Ivan Garcia " + i);

			Customer savedCustomer = customerRepository.save(newCustomer); // almacena newCustomer

			Assert.assertThat(savedCustomer.getId(), CoreMatchers.notNullValue());
			Assert.assertThat(savedCustomer.getId(), CoreMatchers.equalTo(Long.valueOf(i)));
			Assert.assertThat(savedCustomer.getFullName(), CoreMatchers.equalTo("Ivan Garcia " + i));
			Assert.assertThat(savedCustomer.getBirthday(), CoreMatchers.equalTo(LocalDate.of(1987, 06, 23)));
			Assert.assertThat(savedCustomer, CoreMatchers.is(CoreMatchers.sameInstance(newCustomer)));

			log.info("saved customer " + savedCustomer);
			log.info("saved customer is same instance than new customer: " + (savedCustomer == newCustomer));
		}

		List<Customer> customersToSave = new ArrayList<>();
		for (int i = 4; i <= 6; i++) {
			Customer newCustomer = new Customer();
			newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
			newCustomer.setFullName("Ivan Garcia " + i);
			customersToSave.add(newCustomer);
		}

		List<Customer> savedCustomers = customerRepository.saveAll(customersToSave); // almacena de un golpe (batch) todos los customersToSave
		savedCustomers.forEach(c -> log.info("saved customer " + c));

		List<Customer> customers = customerRepository.findAll(); // busca todos los customer
		System.out.println(customers.size());

		long customersSize = StreamSupport.stream(customers.spliterator(), false).count();

		Assert.assertThat(customersSize, CoreMatchers.equalTo(6L));

		customers.forEach(c -> log.info(c.toString()));

		log.info("listAllSimpleCustomer test ends =======================================================");
	}

	@Test
	public void deleteAllSimpleCustomer() {

		log.info("deleteAllSimpleCustomer test starts =======================================================");

		List<Customer> customersToSave = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			Customer newCustomer = new Customer();
			newCustomer.setBirthday(LocalDate.of(1987, 06, 23));
			newCustomer.setFullName("Ivan Garcia " + i);
			customersToSave.add(newCustomer);
		}

		// almacena de un golpe (batch) todos los customersToSave
		customerRepository.saveAll(customersToSave);

		Iterable<Customer> customers = customerRepository.findAll(); // busca todos los customer

		long customersSize = StreamSupport.stream(customers.spliterator(), false).count();

		Assert.assertThat(customersSize, CoreMatchers.equalTo(10L));

		customers.forEach(c -> log.info(c.toString()));

		// borra todos los customer
		customerRepository.deleteAll();

		Iterable<Customer> reloadedCustomers = customerRepository.findAll(); // busca todos los customer

		long reloadedCustomersSize = StreamSupport.stream(reloadedCustomers.spliterator(), false).count();

		Assert.assertThat(reloadedCustomersSize, CoreMatchers.equalTo(0L));

		log.info("deleteAllSimpleCustomer test ends =======================================================");
	}
}