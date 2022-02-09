package com.truper.spring.aop.practicaF.introductions.bean;

import com.truper.spring.aop.practicaF.introductions.bean.api.IStudentAdditionalDetails;

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
public class StudentAdditionalDetails implements IStudentAdditionalDetails {

	private String city;
	private String country;

	@Override
	public void showAdditionalDetails() {
		log.info("I live in : " + country + ", " + city);
	}
}
