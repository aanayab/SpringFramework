package com.truper.spring.core.tarea1.notification.service.api;

import com.truper.spring.core.tarea1.notification.enums.NotificationType;

public interface INotificationService {

	void notifyTo(String receiver, String message,
			NotificationType notificationType);

}
