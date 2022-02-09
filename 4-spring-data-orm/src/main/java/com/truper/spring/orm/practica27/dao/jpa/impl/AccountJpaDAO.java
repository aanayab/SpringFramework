package com.truper.spring.orm.practica27.dao.jpa.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.IAccountDAO;
import com.truper.spring.orm.practica27.dao.jpa.GenericJpaDAO;
import com.truper.spring.orm.practica27.domain.entities.Account;

// Habilitar bean Repository 
@Repository
public class AccountJpaDAO extends GenericJpaDAO<Account, Long> implements IAccountDAO {

	public AccountJpaDAO() {
		super(Account.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findByCustomerId(Long id) {
		return (List<Account>) this.entityManager.createQuery("FROM Account WHERE customer.id=" + id) // JPQL
				.getResultList();
	}

}
