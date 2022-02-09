package com.truper.spring.jdbc.pratica25.dao.springjdbc.object;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import com.truper.spring.jdbc.pratica25.domain.entities.User;

public class UserSqlUpdate extends SqlUpdate implements IUserSqlUpdate {

	private static final String UPDATE = "UPDATE USER_TBL "
									   + "SET "
									   		+ "USERNAME = ?, "
									   		+ "PASSWORD = ? "
									   + "WHERE "
									   		+ "USER_ID = ?";

	@Autowired
	public UserSqlUpdate(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate.getDataSource(), UPDATE);
		declareParameter(new SqlParameter("USERNAME", Types.VARCHAR));
		declareParameter(new SqlParameter("PASSWORD", Types.VARCHAR));
		declareParameter(new SqlParameter("USER_ID", Types.INTEGER));
		compile();
	}

	@Override
	public int updateUser(long id, User user) {
		return update(user.getUsername(), user.getPassword(), id);
	}

}
