package com.truper.spring.core.practica10.beanpostprocessors.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import com.truper.spring.core.practica10.beanpostprocessors.bean.Worker;

public class BeanPostProcessor1 implements BeanPostProcessor, Ordered {

	@Override
	public int getOrder() {
		return 1;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		System.out.println("[Bean Post Process Before Initialization " + this.getOrder() + "]");
		
		if(bean instanceof Worker) {
			Worker w = (Worker)bean;
			w.setName("Pepe");
			w.setAge(18);
			System.out.println("[BPP] Changing Worker data.");
		}
		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		System.out.println("[Bean Post Process After Initialization " + this.getOrder() + "]");
		
		if(bean instanceof Worker) {
			Worker w = (Worker)bean;
			w.setName("Paulina");
			w.setAge(24);
			System.out.println("[BPP] Changing Worker data.");
		}
		
		return bean;
	}

}