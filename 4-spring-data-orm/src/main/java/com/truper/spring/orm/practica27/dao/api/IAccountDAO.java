package com.truper.spring.orm.practica27.dao.api;

import java.util.List;

import com.truper.spring.orm.practica27.dao.IGenericDAO;
import com.truper.spring.orm.practica27.domain.entities.Account;

public interface IAccountDAO extends IGenericDAO<Account, Long> {

	List<Account> findByCustomerId(Long id);
}
