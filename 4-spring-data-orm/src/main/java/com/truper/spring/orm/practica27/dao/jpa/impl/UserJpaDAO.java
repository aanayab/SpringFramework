package com.truper.spring.orm.practica27.dao.jpa.impl;

import org.springframework.stereotype.Repository;

import com.truper.spring.orm.practica27.dao.api.IUserDAO;
import com.truper.spring.orm.practica27.dao.jpa.GenericJpaDAO;
import com.truper.spring.orm.practica27.domain.entities.User;

//Habilitar bean Repository 
@Repository
public class UserJpaDAO extends GenericJpaDAO<User, Long> implements IUserDAO {

	public UserJpaDAO() {
		super(User.class);
	}

}
