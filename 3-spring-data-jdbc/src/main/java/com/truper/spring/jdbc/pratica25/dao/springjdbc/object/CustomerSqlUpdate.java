package com.truper.spring.jdbc.pratica25.dao.springjdbc.object;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.truper.spring.jdbc.pratica25.domain.entities.Customer;

public class CustomerSqlUpdate extends SqlUpdate implements ICustomerSqlUpdate {

	private static final String UPDATE = "UPDATE CUSTOMER_TBL "
									   + "SET "
									   		+ "NAME = ?, " 
									   		+ "LAST_NAME = ? "
									   + "WHERE "
									   		+ "CUSTOMER_ID = ?";

	@Autowired
	public CustomerSqlUpdate(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate.getDataSource(), UPDATE);
		declareParameter(new SqlParameter("NAME", Types.VARCHAR));
		declareParameter(new SqlParameter("LAST_NAME", Types.VARCHAR));
		declareParameter(new SqlParameter("CUSTOMER_ID", Types.INTEGER));
		compile();
	}

	@Override
	public int updateCustomer(long id, Customer customer) {
		return this.update(customer.getName(), customer.getLastName(), id);
	}
}
