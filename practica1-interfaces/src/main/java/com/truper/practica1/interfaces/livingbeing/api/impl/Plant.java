package com.truper.practica1.interfaces.livingbeing.api.impl;

import com.truper.practica1.interfaces.livingbeing.api.ILivingBeing;

import lombok.Data;

@Data
public abstract class Plant implements ILivingBeing {
	private String type = "Plant";
	private String subType;

	@Override
	public void born() {
		System.out.println("born like a " + this.getType() + " (" + this.getSubType() + ")");
	}

}
