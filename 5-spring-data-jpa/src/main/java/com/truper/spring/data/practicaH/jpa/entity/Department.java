package com.truper.spring.data.practicaH.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "DEPARTMENT_TBL")
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEPARTMENT_ID")
	private Long id;

	@Column(name = "DEPARTMENT_NAME")
	private String name;
	
	@OneToOne
    private Staff chair;

	@OneToMany(mappedBy = "department", 
			   fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();

	public Department(String name) {
		super();
		this.name = name;
	}
	
	public Department(String name, Staff chair) {
		super();
		this.name = name;
		this.chair = chair;
	}
}
