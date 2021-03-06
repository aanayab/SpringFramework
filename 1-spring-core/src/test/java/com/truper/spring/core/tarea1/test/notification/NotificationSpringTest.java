package com.truper.spring.core.tarea1.test.notification;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.tarea1.notification.enums.NotificationType;
import com.truper.spring.core.tarea1.notification.service.api.INotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationSpringTest {

	private static ClassPathXmlApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		applicationContext = new ClassPathXmlApplicationContext("spring/tarea1/notification-application-context.xml");
	}

	@Test
	public void notificationSpringTest() {

		log.info("notificationSpringTest -------------------");

		INotificationService notificacionService = (INotificationService) applicationContext
				.getBean(INotificationService.class);

		Assert.assertNotNull(notificacionService);

		notificacionService
				.notifyTo("Ivan", "Bienvenidos al Workshop de Spring 5",
						NotificationType.TWITTER);
	}

}
