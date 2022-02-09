package com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.truper.spring.jdbc.pratica25.domain.entities.Account;
import com.truper.spring.jdbc.pratica25.domain.entities.Customer;
import com.truper.spring.jdbc.pratica25.domain.vo.CustomDate;

@Component
public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();

		Customer customer = new Customer();
		customer.setId(rs.getLong("FK_CUSTOMER_ID"));

		account.setCustomer(customer);

		account.setId(rs.getLong("ACCOUNT_ID"));
		account.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
		account.setCreatedDate(new CustomDate(rs.getDate("CREATED_DATE").getTime()));
		account.setBalance(rs.getBigDecimal("BALANCE"));

		return account;
	}

}
