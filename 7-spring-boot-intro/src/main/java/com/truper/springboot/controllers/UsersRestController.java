package com.truper.springboot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truper.springboot.controllers.dto.UserResponse;
import com.truper.springboot.converters.UserEntityConverter;
import com.truper.springboot.mongo.documents.UserEntity;
import com.truper.springboot.mongo.repositories.UserEntityRepository;

@RestController
@RequestMapping("/api")
public class UsersRestController {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private UserEntityConverter userEntityConverter;

	@GetMapping("/user-entity")
	public List<UserEntity> userEntity() {
		return userEntityRepository.findAll();
	}

	@GetMapping("/user")
	public List<UserResponse> userResponse() {
		return userEntityRepository.findAll()
								   .stream()
								   .map(userEntityConverter::convert)
								   .collect(Collectors.toList());
	}
}
