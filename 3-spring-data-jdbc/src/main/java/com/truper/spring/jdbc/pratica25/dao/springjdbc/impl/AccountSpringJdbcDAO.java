package com.truper.spring.jdbc.pratica25.dao.springjdbc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.truper.spring.jdbc.pratica25.dao.api.IAccountDAO;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import com.truper.spring.jdbc.pratica25.dao.springjdbc.rowmapper.AccountRowMapper;
import com.truper.spring.jdbc.pratica25.domain.entities.Account;

@Repository
@Profile({ "h2-in-memory", "h2-local", "mysql" })
public class AccountSpringJdbcDAO extends GenericSpringJdbcDAO<Account, Long> implements IAccountDAO {

	private static final String SELECT_ALL_ACCOUNT_WHERE_CUSTOMER_ID = "SELECT * FROM ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?";
	private static final String SELECT_ALL_ACCOUNT = "SELECT * FROM ACCOUNT_TBL";
	private static final String SELECT_ACCOUNT = "SELECT * FROM ACCOUNT_TBL WHERE ACCOUNT_ID = ?";

	private static final String INSERT_ACCOUNT = "INSERT INTO ACCOUNT_TBL VALUES (null, :fkCustomerId, :accountNumber, :createdDate, :balance)";

	private static final String UPDATE_ACCOUNT_WHERE_ACCOUNT_ID = "UPDATE ACCOUNT_TBL SET ACCOUNT_NUMBER = ?, CREATED_DATE = ?, BALANCE = ? WHERE ACCOUNT_ID = ?";

	private static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT_TBL WHERE ACCOUNT_ID = ?";

	@Autowired
	AccountRowMapper accountRowMapper;

	@Override
	public List<Account> findByCustomerId(Long id) {

		// FIND ACCOUNT BY CUSTOMER ID

		// Implementar mediante JdbcTemplate y AccountRowMapper
		// Se espera una lista de objetos
		return this.jdbcTemplate.query(SELECT_ALL_ACCOUNT_WHERE_CUSTOMER_ID, new AccountRowMapper(), id);
	}

	@Override
	public void insert(Account account) {
		// INSERT Account

		// Implementar mediante NamedParameterJdbcTemplate, SqlParameterSource y
		// KeyHolder
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("fkCustomerId", account.getCustomer().getId())
				.addValue("accountNumber", account.getAccountNumber()).addValue("createdDate", account.getCreatedDate())
				.addValue("balance", account.getBalance());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.namedJdbcTemplate.update(INSERT_ACCOUNT, parameters, keyHolder);

		account.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(Account account) {
		// UPDATE ACCOUNT

		// Implementar mediante JdbcTemplate
		this.jdbcTemplate.update(UPDATE_ACCOUNT_WHERE_ACCOUNT_ID, account.getAccountNumber(), account.getCreatedDate(),
				account.getBalance(), account.getId());
	}

	@Override
	public Account findById(Long id) {
		Account account = null;

		// FIND ACCOUNT BY ID
		try {

			// Implementar mediante JdbcTemplate y AccountRowMapper
			// Se espera un unico objeto

			account = this.jdbcTemplate.queryForObject(SELECT_ACCOUNT, accountRowMapper, id);

		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}

		return account;
	}

	@Override
	public Account delete(Long id) {
		Account account = this.findById(id);
		return this.delete(account);
	}

	@Override
	public Account delete(Account account) {
		if (account == null)
			return account;

		// DELETE ACCOUNT
		// Implementar mediante JdbcTemplate
		this.jdbcTemplate.update(DELETE_ACCOUNT, account.getId());

		return account;
	}

	@Override
	public List<Account> findAll() {
		List<Account> userList = null;

		// FIND ALL Account
		// Implementar mediante JdbcTemplate y AccountRowMapper
		userList = this.namedJdbcTemplate.query(SELECT_ALL_ACCOUNT, accountRowMapper);

		return userList;
	}

}
