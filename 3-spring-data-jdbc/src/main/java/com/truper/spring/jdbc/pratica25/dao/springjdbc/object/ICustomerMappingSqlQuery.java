package com.truper.spring.jdbc.pratica25.dao.springjdbc.object;

import com.truper.spring.jdbc.pratica25.domain.entities.Customer;

public interface ICustomerMappingSqlQuery {

	Customer findCustomerById(long id);
}
