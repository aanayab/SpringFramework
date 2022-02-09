package com.truper.spring.data.practicaJ.jdbc.repositories;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.truper.spring.data.practicaJ.jdbc.entity.Department;

// Extiende de CrudRepository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

	// Analiza metodo @Query
	@Query("SELECT DEPARTMENT.id AS id, DEPARTMENT.name AS name, chair.department AS chair_department, chair.name AS chair_name "
			+ "FROM DEPARTMENT LEFT OUTER JOIN CHAIR AS chair ON chair.DEPARTMENT = DEPARTMENT.id "
			+ "WHERE DEPARTMENT.name =:name")
	Optional<Department> findByName(@Param("name") String name);
}
