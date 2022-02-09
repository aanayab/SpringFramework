package com.truper.spring.data.practicaI.mongodb.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.truper.spring.data.practicaI.mongodb.document.Department;
import com.truper.spring.data.practicaI.mongodb.document.Staff;

public class DepartmentCustomRepositoryImpl implements DepartmentCustomRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<Department> findDepartmentByChairFirstName(String firstName) {
		Query query = new Query(Criteria.where("member.firstName").is(firstName));
		List<Staff> staffList = mongoTemplate.find(query, Staff.class);
		
		List<Department> departmentsList = new ArrayList<>();
		
		if(!staffList.isEmpty()) {
			for(Staff staff : staffList) {
				Query query2 = new Query(Criteria.where("chair.$id").is(staff.getId()));
				List<Department> departments = mongoTemplate.find(query2, Department.class);
				
				departmentsList.addAll(departments);
			}
		}
		
		return departmentsList;
	}
}
