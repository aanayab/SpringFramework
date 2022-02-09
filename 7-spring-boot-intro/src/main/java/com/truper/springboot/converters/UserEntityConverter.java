package com.truper.springboot.converters;

import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.truper.springboot.controllers.dto.UserResponse;
import com.truper.springboot.mongo.documents.UserEntity;

@Component
public class UserEntityConverter implements Converter<UserEntity, UserResponse>{

	@Override
	public UserResponse convert(UserEntity source) {
		return UserResponse.builder()
				.id(source.getId())
				.name(source.getName())
				.age(source.getAge())
				.username(source.getUsername())
				.roles(source.getRoles()
							 .stream()
							 .map(rol -> rol.substring(5))
							 .collect(Collectors.toList()))
				.build();
	}

}
