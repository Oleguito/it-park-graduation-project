package ru.itpark.projectservice.application.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;

@Service
@RequiredArgsConstructor
public class MessageConstructor {

    @Autowired
    private final Map<String, NotificationProcessor> processors;

    public void processNotification(InvitationMessage notification) {
        NotificationProcessor notificationProcessor = processors.get(notification.getType());

        if (notificationProcessor == null) {
            throw new RuntimeException("Unknown notification type: " + notification.getType());
        }

        notificationProcessor.processNotification(notification);

    }

}
