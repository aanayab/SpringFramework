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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "COURSE_TBL")
@NoArgsConstructor
@ToString(exclude = {"department"})
@EqualsAndHashCode(exclude = {"department"})
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_ID")
	private Long id;

	@Column(name = "COURSE_NAME")
	private String name;
	
	@Column(name = "COURSE_CREDITS")
	private Integer credits;

	@OneToOne
	private Staff instructor; // default la columna "nombre_de_la_propiedad"_id.
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Course> prerequisites = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "COURSE_DEPT_ID")
	private Department department;

	public Course(String name, int credits, Staff instructor, Department department) {
		super();
		this.name = name;
		this.credits = credits;
		this.instructor = instructor;
		this.department = department;
	}
	
	public Course addPrerequisite(Course prerequisite){
        prerequisites.add(prerequisite);
        return this;
    }
}
