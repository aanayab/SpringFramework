package com.truper.spring.data.practicaJ.jdbc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// Define Tabla "CHAIR"
@Table("CHAIR")
@NoArgsConstructor
public class Chair {

	// Define Id
	@Id
	private Integer department;

	private String name;

	public Chair(String name) {
		this.name = name;
	}
}
