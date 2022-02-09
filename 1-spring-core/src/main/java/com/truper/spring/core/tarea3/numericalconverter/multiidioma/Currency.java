package com.truper.spring.core.tarea3.numericalconverter.multiidioma;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
	private String pluralCurrency;
	private String singularCurrency;
}
