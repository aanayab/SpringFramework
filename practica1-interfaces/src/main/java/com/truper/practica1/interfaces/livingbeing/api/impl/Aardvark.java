package com.truper.practica1.interfaces.livingbeing.api.impl;

import com.truper.practica1.interfaces.livingbeing.api.IBugEater;

public class Aardvark extends Animal implements IBugEater{

	// Implementar
	public Aardvark() {
		this.setSubType("Aardvark");
	}

	@Override
	public void grow() {
		System.out.println("grow like a " + this.getType() + " (" + this.getSubType() + ")");
	}

	@Override
	public void breed() {
		System.out.println("breed like a " + this.getType() + " (" + this.getSubType() + ")");
	}

	@Override
	public void die() {
		System.out.println("die like a " + this.getType() + " (" + this.getSubType() + ")");
	}

	@Override
	public void eatBug() {
		System.out.println("eat bug like a " + this.getType() + " (" + this.getSubType() + ")");
	}
	
}
