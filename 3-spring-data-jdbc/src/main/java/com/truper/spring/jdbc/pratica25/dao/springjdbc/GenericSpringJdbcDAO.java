package com.truper.spring.jdbc.pratica25.dao.springjdbc;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.truper.spring.jdbc.pratica25.dao.IGenericDAO;

import lombok.Getter;

public abstract class GenericSpringJdbcDAO<T, ID extends Serializable> implements IGenericDAO<T, ID> {

	// Inyectar JdbcTemplate
	@Autowired
	protected @Getter JdbcTemplate jdbcTemplate;

	// Inyectar NamedParameterJdbcTemplate
	@Autowired
	protected @Getter NamedParameterJdbcTemplate namedJdbcTemplate;

}
