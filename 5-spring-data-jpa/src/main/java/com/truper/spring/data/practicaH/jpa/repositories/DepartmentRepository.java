package com.truper.spring.data.practicaH.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truper.spring.data.practicaH.jpa.entity.Department;

// Extiende de JpaRepository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
}
