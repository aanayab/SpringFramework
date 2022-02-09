package com.truper.spring.core.practica10.beanpostprocessors.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Worker {
	private @Getter @Setter String name;
	private @Getter @Setter int age;

	public Worker() {
		this.name = "Ivan Garcia";
		this.age = 10;

		System.out.println("[Construct] Worker name: " + this.name);
		System.out.println("[Construct] Worker name: " + this.age);
	}

	public void init() {
		System.out.println("[init] Initializing Worker.");

		System.out.println("[init] Worker name: " + this.name);
		System.out.println("[init] Worker age: " + this.age);

		this.name = "Worker 1";

		System.out.println("[init] Worker name: " + this.name);
		System.out.println("[init] Worker age: " + this.age);
	}

	public void showInfo() {
		String msg = String.format("> I'm Worker: %s. [%s]", this.name, this.hashCode());

		System.out.println("[showInfo] " + msg);
		
		throw new RuntimeException("Worker murio en la chamba.");
	}

	public void destroy() {
		System.out.println("[destroy] Destructing Worker.");
	}
}
