package com.truper.spring.orm.practica27.dao.jpa.impl;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.ICustomerDAO;
import com.truper.spring.orm.practica27.dao.jpa.GenericJpaDAO;
import com.truper.spring.orm.practica27.domain.entities.Customer;

//Habilitar bean Repository 
@Repository
public class CustomerJpaDAO extends GenericJpaDAO<Customer, Long> implements ICustomerDAO {

	public CustomerJpaDAO() {
		super(Customer.class);
	}

}
