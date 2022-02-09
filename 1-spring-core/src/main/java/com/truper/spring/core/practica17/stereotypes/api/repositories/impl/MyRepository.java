package com.truper.spring.core.practica17.stereotypes.api.repositories.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.truper.spring.core.practica17.stereotypes.api.IRepositoryClass;

import lombok.Data;

@Data
// Anotar repositorio, implementar Interface IRepositoryClass
@Repository
public class MyRepository implements IRepositoryClass {

	// Inyectar con "resource" bean repositoryClassName
	@Resource(name = "repositoryClassName")
	private String name;

}
