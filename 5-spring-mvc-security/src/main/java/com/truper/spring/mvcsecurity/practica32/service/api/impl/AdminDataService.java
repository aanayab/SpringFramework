package com.truper.spring.mvcsecurity.practica32.service.api.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.truper.spring.mvcsecurity.practica32.service.api.IDataService;

@Service("admin-data-service")
public class AdminDataService implements IDataService {

	@Override
	// Pre Authorize ROLE_ADMIN
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getData() {
		return "admin DATA ....";
	}

}
