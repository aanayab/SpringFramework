package com.truper.spring.core.practicaC.filteringcomponents.bean.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
//Anotar como Repositorio
@Repository
public class CarRepository {

	// Inyectar
	@Value("jdbc:mysql://localhost:3306/my-bd")
	public String repoURL;
}
