package com.truper.spring.mvcsecurity.practica32.service.api.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.truper.spring.mvcsecurity.practica32.service.api.IDataService;

@Service("root-data-service")
public class RootDataService implements IDataService {

	@Override
	// Pre Authorize ROLE_ROOT
	@PreAuthorize("hasRole('ROLE_ROOT')")
	public String getData() {
		return "root DATA ....";
	}

}
