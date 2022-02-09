package com.truper.spring.data.practicaH.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Column(name = "STUDENT_FIRST_NAME")
	private String firstName;

	@Column(name = "STUDENT_LAST_NAME")
	private String lastName;
}
