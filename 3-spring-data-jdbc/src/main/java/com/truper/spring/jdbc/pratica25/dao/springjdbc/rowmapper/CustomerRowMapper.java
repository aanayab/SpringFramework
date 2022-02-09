package com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.truper.spring.jdbc.pratica25.domain.entities.Customer;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

	@Autowired
	private UserRowMapper userRowMapper;

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return userRowMapper.mapRow(rs, rowNum).getCustomer();
	}

}
