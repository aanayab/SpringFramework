package com.truper.spring.data.practicaI.mongodb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.truper.spring.data.practicaI.mongodb.document.Department;

// Extiende de MongoRepository
public interface DepartmentRepository extends MongoRepository<Department, Integer>, DepartmentCustomRepository {

	// Define los metodos conforme la practica lo requiera (guiado por instructor)
	Department findByName(String name);

	@Query("{ 'name' : { $regex: ?0 } }")
	List<Department> findDepartmentByNamePattern(String pattern);

	List<Department> findDepartmentByNameContainingIgnoreCase(String name);
	
	@Deprecated
	List<Department> findDepartmentByChairMemberFirstName(String firstName);
}

