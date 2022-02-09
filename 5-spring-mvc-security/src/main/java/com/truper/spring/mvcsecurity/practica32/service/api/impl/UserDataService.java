package com.truper.spring.mvcsecurity.practica32.service.api.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.truper.spring.mvcsecurity.practica32.service.api.IDataService;

@Service("user-data-service")
public class UserDataService implements IDataService {

	@Override
	// Pre Authorize ROLE_USER
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getData() {
		return "user DATA ....";
	}

}
