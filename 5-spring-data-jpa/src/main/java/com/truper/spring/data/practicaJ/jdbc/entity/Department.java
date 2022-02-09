package com.truper.spring.data.practicaJ.jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// Define Tabla "DEPARTMENT"
@Table("DEPARTMENT")
public class Department {
	
	// Define Id
	@Id
	private Integer id;

	private String name;

	private Chair chair;

	public Department(String name, Chair chair) {

		this.name = name;
		this.chair = chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Chair getChair() {
		return chair;
	}

	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", name='" + name + '\'' + ", chair=" + chair + '}';
	}
}
