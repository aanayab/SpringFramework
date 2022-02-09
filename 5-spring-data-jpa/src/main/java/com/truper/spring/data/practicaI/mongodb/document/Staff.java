package com.truper.spring.data.practicaI.mongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//Define como Documento
@Document
@NoArgsConstructor
public class Staff {

	// Define como Id
	@Id
	private Integer id;

	private Person member;

	public Staff(Integer id, Person member) {
		this.id = id;
		this.member = member;
	}
}
