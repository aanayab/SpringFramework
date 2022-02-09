package com.truper.spring.data.practicaH.jpa.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseView {
	private String name;
	private String instructorLastName;
	private String deptName;
}