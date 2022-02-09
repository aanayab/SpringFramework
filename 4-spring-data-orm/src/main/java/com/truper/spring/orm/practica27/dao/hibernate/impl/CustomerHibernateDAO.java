package com.truper.spring.orm.practica27.dao.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.ICustomerDAO;
import com.truper.spring.orm.practica27.dao.hibernate.GenericHibernateDAO;
import com.truper.spring.orm.practica27.domain.entities.Customer;

//Habilitar bean Repository 
@Repository
public class CustomerHibernateDAO extends GenericHibernateDAO<Customer, Long> implements ICustomerDAO {

	public CustomerHibernateDAO() {
		super(Customer.class);
	}

}
