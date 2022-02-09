package com.truper.spring.core.practicaE.propertyeditor.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCard {

	private String rawCardNumber;

	private Integer bankIdNo;

	private Integer accountNo;

	private Integer checkCode;

}
