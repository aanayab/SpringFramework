package com.truper.spring.core.practica10.beanpostprocessors.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import com.truper.spring.core.practica10.beanpostprocessors.bean.Worker;
import com.truper.spring.core.practica10.beanpostprocessors.bean.proxy.WorkerProxy;

public class BeanPostProcessor3 implements BeanPostProcessor, Ordered {

	@Override
	public int getOrder() {
		return 3;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("[Bean Post Process Before Initialization " + this.getOrder() + "]");

		if (bean instanceof Worker) {
			Worker w = (Worker) bean;

			WorkerProxy wp = new WorkerProxy(w);
			bean = wp;

			System.out.println("[BPP] Proxying Worker.");
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("[Bean Post Process After Initialization " + this.getOrder() + "]");

		if (bean instanceof Worker && bean instanceof WorkerProxy) {
			WorkerProxy w = (WorkerProxy) bean;
			w.setName("Karla");
			w.setAge(31);
			System.out.println("[BPP] Changing WorkerProxy data.");
		}

		return bean;
	}

}