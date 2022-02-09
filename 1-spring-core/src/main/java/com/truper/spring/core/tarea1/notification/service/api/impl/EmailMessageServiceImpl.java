package com.truper.spring.core.tarea1.notification.service.api.impl;

import com.truper.spring.core.tarea1.messaging.service.api.IMessageService;

public class EmailMessageServiceImpl implements IMessageService {

	@Override
	public void sendMessage(String receiver, String message) {
		System.out.println(">>> EMAIL -------------");
		System.out.println("enviando email a: " + receiver);
		System.out.println("mensaje: " + message);
		System.out.println("-------------");
	}

}
