package com.truper.spring.core.practicaC.filteringcomponents.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
// Anotar como bean
@Component
public class BabyBiker extends Biker {

	// Inyectar
	@Value("3")
	public int age;
}
