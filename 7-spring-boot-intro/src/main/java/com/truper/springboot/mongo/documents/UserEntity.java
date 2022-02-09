package com.truper.springboot.mongo.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserEntity {

	@Id
	private String id;

	private String name;

	private int age;

	private String username;

	private String password;

	private List<String> roles; // ROLE_NOMBRE
}
