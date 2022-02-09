package com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.truper.spring.jdbc.pratica25.domain.entities.Customer;
import com.truper.spring.jdbc.pratica25.domain.entities.User;

@Component
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = Customer.builder()
				.id(rs.getLong("CUSTOMER_ID"))
				.name(rs.getString("NAME"))
				.lastName(rs.getString("LAST_NAME"))
				.build();
		
		User user = User.builder()
				.id(rs.getLong("USER_ID"))
				.username(rs.getString("USERNAME"))
				.password(rs.getString("PASSWORD"))
				.customer(customer)
				.build();
		customer.setUser(user);
		return user;
	}

}
