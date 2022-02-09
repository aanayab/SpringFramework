package com.truper.spring.jdbc.pratica25.dao.springjdbc.mapper;

import com.truper.spring.jdbc.pratica25.domain.entities.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {

	private Long userId;
	private Long fkCustomerId;
	private String username;
	private String password;

	public static UserEntity map(User user) {
		return UserEntity.builder()
				.userId(user.getId())
				.fkCustomerId(user.getCustomer().getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
	}

}
