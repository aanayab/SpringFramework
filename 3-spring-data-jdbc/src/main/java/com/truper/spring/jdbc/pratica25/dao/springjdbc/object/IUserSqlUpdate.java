package com.truper.spring.jdbc.pratica25.dao.springjdbc.object;

import com.truper.spring.jdbc.pratica25.domain.entities.User;

public interface IUserSqlUpdate {

	int updateUser(long id, User user);
}
