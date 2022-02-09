package com.truper.spring.orm.practica27.dao.hibernate.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.IAccountDAO;
import com.truper.spring.orm.practica27.dao.hibernate.GenericHibernateDAO;
import com.truper.spring.orm.practica27.domain.entities.Account;

// Habilitar bean Repository 
@Repository
public class AccountHibernateDAO extends GenericHibernateDAO<Account, Long> implements IAccountDAO {

	public AccountHibernateDAO() {
		super(Account.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findByCustomerId(Long id) {
		return (List<Account>) this.sessionFactory.getCurrentSession()
				.createQuery("FROM Account WHERE customer.id=" + id) // JPQL
				.list();
	}

}
