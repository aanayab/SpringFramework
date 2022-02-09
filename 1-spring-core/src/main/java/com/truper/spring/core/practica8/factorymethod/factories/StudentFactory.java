package com.truper.spring.core.practica8.factorymethod.factories;

import com.truper.spring.core.practica8.factorymethod.bean.Student;

public class StudentFactory {

	public Student build(String materia, String name) {
		return Student.constructStudent(name, materia);
	}
}
