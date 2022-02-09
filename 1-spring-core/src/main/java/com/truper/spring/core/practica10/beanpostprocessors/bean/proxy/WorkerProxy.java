package com.truper.spring.core.practica10.beanpostprocessors.bean.proxy;

import com.truper.spring.core.practica10.beanpostprocessors.bean.Worker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class WorkerProxy extends Worker {

	@Setter(AccessLevel.NONE)
	private Worker target;

	@Override
	public String getName() {
		return target.getName();
	}

	@Override
	public void setName(String name) {
		target.setName(name);
	}

	@Override
	public int getAge() {
		return target.getAge();
	}

	@Override
	public void setAge(int age) {
		target.setAge(age);
	}

	@Override
	public void init() {
		System.out.println("[Worker Proxy] Hola !");
		target.init();
		System.out.println("[Worker Proxy] Adios !");
	}

	@Override
	public void showInfo() {

		try {
			target.showInfo();
		} catch (RuntimeException ex) {
			System.out.println("[Worker Proxy] ex: " + ex.getMessage());
		} finally {
			System.out.println("[Worker Proxy] El Worker siempre no murio.");
		}

	}

	@Override
	public void destroy() {
		System.out.println("[Worker Proxy] Preparandome para ser destruido =(");
		target.destroy();
		System.out.println("[Worker Proxy] Me destruyeron");
	}

}
