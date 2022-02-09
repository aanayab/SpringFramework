package com.truper.spring.core.practica15.autowired.properties.bean;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class Pencil {
	@Autowired
	private Color inkColor;
}
