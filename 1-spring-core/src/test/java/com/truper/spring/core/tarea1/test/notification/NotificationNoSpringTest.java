package com.truper.spring.core.tarea1.test.notification;

import org.junit.Assert;
import org.junit.Test;

import com.truper.spring.core.tarea1.messaging.service.api.impl.NotificationServiceImpl;
import com.truper.spring.core.tarea1.notification.enums.NotificationType;
import com.truper.spring.core.tarea1.notification.service.api.INotificationService;
import com.truper.spring.core.tarea1.notification.service.api.impl.EmailMessageServiceImpl;
import com.truper.spring.core.tarea1.notification.service.api.impl.FacebookMessageServiceImpl;
import com.truper.spring.core.tarea1.notification.service.api.impl.TwitterMessageServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotificationNoSpringTest {

	@Test
	public void notificationNoSpringTest() {
		log.info("notificationNoSpringTest -------------------");

		INotificationService notificacionService = new NotificationServiceImpl();

		((NotificationServiceImpl) notificacionService)
				.setEmailMessageService(new EmailMessageServiceImpl());

		((NotificationServiceImpl) notificacionService)
				.setFacebookMessageService(new FacebookMessageServiceImpl());

		((NotificationServiceImpl) notificacionService)
				.setTwitterMessageService(new TwitterMessageServiceImpl());

		Assert.assertNotNull(notificacionService);

		notificacionService.notifyTo("Ivan",
				"Bienvenidos al Workshop de Spring 5", NotificationType.FACEBOOK);
	}

}
