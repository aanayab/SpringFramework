package com.truper.spring.data.practicaH.jpa.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "STUDENT_TBL")
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDENT_ID")
	private Long id;

	@Embedded
	private Person attendee;

	@Column(name = "STUDENT_FULLTIME")
	private boolean fullTime;

	@Column(name = "STUDENT_BIRTHDAY")
	private LocalDate birthday;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ENROLLMENT_TBL", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "COURSE_ID") })
	private List<Course> courses = new ArrayList<>();

	public Student(Person attendee, boolean fullTime, LocalDate birthday) {
		super();
		this.attendee = attendee;
		this.fullTime = fullTime;
		this.birthday = birthday;
	}
}
