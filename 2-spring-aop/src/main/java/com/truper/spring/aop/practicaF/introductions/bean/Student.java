package com.truper.spring.aop.practicaF.introductions.bean;

import com.truper.spring.aop.practicaF.introductions.bean.api.IStudent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Student implements IStudent {

	private int studentNumber;
	private String name;

	@Override
	public void getInfo() {
		log.info("My name is: " + name + ", and my student number is: " + studentNumber);
	}
}
