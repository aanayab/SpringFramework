package com.truper.spring.jdbc.pratica25.dao.springjdbc.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper.CustomerRowMapper;
import com.truper.spring.jdbc.pratica25.domain.entities.Customer;

public class CustomerMappingSqlQuery extends MappingSqlQuery<Customer> implements ICustomerMappingSqlQuery {

	private static final String SELECT = "SELECT * "
									   + "FROM CUSTOMER_TBL INNER JOIN USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID "
									   + "WHERE CUSTOMER_ID = ?";
	
	
	private CustomerRowMapper customerRowMapper;
	
	@Autowired
	public CustomerMappingSqlQuery(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
		super(jdbcTemplate.getDataSource(), SELECT);
		this.customerRowMapper = customerRowMapper;
		declareParameter(new SqlParameter("CUSTOMER_ID", Types.INTEGER));
		compile();
	}
	
	@Override
	protected Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return customerRowMapper.mapRow(rs, rowNum);
	}

	@Override
	public Customer findCustomerById(long id){
		return this.findObject(id);
	}
	
}
