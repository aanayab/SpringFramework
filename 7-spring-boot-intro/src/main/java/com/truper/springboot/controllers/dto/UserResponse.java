package com.truper.springboot.controllers.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

	private String id;

	private String name;

	private int age;

	private String username;

	private List<String> roles; // NOMBRE
}
