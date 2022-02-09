package com.truper.spring.jdbc.pratica25.dao.springjdbc.impl.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.truper.spring.jdbc.pratica25.dao.api.ICustomerDAO;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.mapper.UserEntity;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.CustomerMappingSqlQuery;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.CustomerSqlUpdate;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.ICustomerMappingSqlQuery;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.ICustomerSqlUpdate;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.IUserSqlUpdate;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.object.UserSqlUpdate;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper.CustomerRowMapper;
import com.truper.spring.jdbc.pratica25.domain.entities.Customer;
import com.truper.spring.jdbc.pratica25.domain.entities.User;

// activas perfil "mysql" ... que beans se levantan? se levanta el mysql y los beans del perfil default
// todos los beans sin perfil son del perfil default

@Repository
@Profile("mysql")
public class CustomerSpringJdbcDAO extends GenericSpringJdbcDAO<Customer, Long>
		implements ICustomerDAO, InitializingBean {

	private SimpleJdbcCall readCustomerProcedure;

	private SimpleJdbcInsert insertCustomer;
	private SimpleJdbcInsert insertUser;

	private ICustomerMappingSqlQuery customerSqlQuery;
	private ICustomerSqlUpdate customerSqlUpdate;
	private IUserSqlUpdate userSqlUpdate;
	
	@Autowired
	private CustomerRowMapper customerRowMapper;

	private static final String SELECT_ALL_CUSTOMER_USER = "SELECT * FROM CUSTOMER_TBL, USER_TBL WHERE CUSTOMER_ID = FK_CUSTOMER_ID";

	@Override
	public void afterPropertiesSet() throws Exception {

		this.readCustomerProcedure 	= new SimpleJdbcCall(this.jdbcTemplate)
											.withProcedureName("read_customer_user");

		this.insertCustomer 		= new SimpleJdbcInsert(this.jdbcTemplate)
											.withTableName("CUSTOMER_TBL")
											.usingGeneratedKeyColumns("CUSTOMER_ID");

		this.insertUser 			= new SimpleJdbcInsert(this.jdbcTemplate)
											.withTableName("USER_TBL")
											.usingGeneratedKeyColumns("USER_ID");

		this.customerSqlQuery 		= new CustomerMappingSqlQuery(this.jdbcTemplate, customerRowMapper);

		this.customerSqlUpdate 		= new CustomerSqlUpdate(this.jdbcTemplate);
		this.userSqlUpdate 			= new UserSqlUpdate(this.jdbcTemplate);
	}

	@Override
	public void insert(Customer customer) {

		// INSERT CUSTOMER
		KeyHolder keyHolder = this.insertCustomer.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(customer));

		customer.setId(keyHolder.getKey().longValue());

		// INSERT USER User => UserEntity
		UserEntity userEntity = UserEntity.map(customer.getUser());

		keyHolder = this.insertUser.executeAndReturnKeyHolder(new BeanPropertySqlParameterSource(userEntity));

		customer.getUser().setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Customer customer) {

		// UPDATE CUSTOMER
		this.customerSqlUpdate.updateCustomer(customer.getId(), customer);

		// UPDATE USER
		this.userSqlUpdate.updateUser(customer.getUser().getId(), customer.getUser());
	}

	@Override
	public Customer findById(Long id) {
		if (id % 2 == 0)
			return customerSqlQuery.findCustomerById(id);
		
		return findCustomerByStoredProcedure(id);
	}

	private Customer findCustomerByStoredProcedure(Long id) {
		// FIND USER OF CUSTOMER BY CUSTOMER_ID
		SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("in_customerId", id);

		Map<String, Object> out = readCustomerProcedure.execute(parameterSource);

		if ((Integer) out.get("#update-count-1") == 0)
			return null;

		User u = new User();
		Customer c = new Customer();

		u.setId(new Long((Integer) out.get("out_user_id")));
		u.setUsername((String) out.get("out_username"));
		u.setPassword((String) out.get("out_password"));

		c.setId(new Long((Integer) out.get("out_customer_id")));
		c.setName((String) out.get("out_name"));
		c.setLastName((String) out.get("out_last_name"));
		c.setUser(u);
		c.getUser().setCustomer(c);

		return c;
	}

	@Override
	public Customer delete(Long id) {
		Customer customer = this.findById(id);
		return this.delete(customer);
	}

	@Override
	public Customer delete(Customer entity) {
		if (entity == null)
			return entity;

		// DELETE COMPLETE RELATIONS OF CUSTOMER WITH ALL TABLES
		final String DELETE_ACCOUNT_TABLE 	= "DELETE FROM ACCOUNT_TBL WHERE FK_CUSTOMER_ID = :customerId";
		final String DELETE_USER_TABLE 		= "DELETE FROM USER_TBL WHERE USER_ID = :userId";
		final String DELETE_CUSTOMER_TABLE 	= "DELETE FROM CUSTOMER_TBL WHERE CUSTOMER_ID = :customerId";

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", entity.getId());
		paramMap.put("userId", entity.getUser().getId());

		this.namedJdbcTemplate.update(DELETE_ACCOUNT_TABLE, paramMap);
		this.namedJdbcTemplate.update(DELETE_USER_TABLE, paramMap);
		this.namedJdbcTemplate.update(DELETE_CUSTOMER_TABLE, paramMap);

		return entity;
	}

	@Override
	public List<Customer> findAll() {

		List<Customer> customerList;

		// FIND COMPLETE ALL CUSTOMER (WITH USER)
		customerList = this.namedJdbcTemplate.query(SELECT_ALL_CUSTOMER_USER, customerRowMapper);

		return customerList;
	}

}
