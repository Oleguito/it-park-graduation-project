package ru.itpark.projectservice.application.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.sharedlib.InvitationMessage;

import java.util.Map;

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
