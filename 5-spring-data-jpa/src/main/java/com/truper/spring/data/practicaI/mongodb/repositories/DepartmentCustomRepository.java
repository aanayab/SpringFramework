package com.truper.spring.data.practicaI.mongodb.repositories;

import java.util.List;

import com.truper.spring.data.practicaI.mongodb.document.Department;

public interface DepartmentCustomRepository {

	List<Department> findDepartmentByChairFirstName(String firstName);
}
