package com.truper.spring.orm.practica27.dao.hibernate.impl;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.IUserDAO;
import com.truper.spring.orm.practica27.dao.hibernate.GenericHibernateDAO;
import com.truper.spring.orm.practica27.domain.entities.User;

//Habilitar bean Repository 
@Repository
public class UserHibernateDAO extends GenericHibernateDAO<User, Long> implements IUserDAO {

	public UserHibernateDAO() {
		super(User.class);
	}

}
