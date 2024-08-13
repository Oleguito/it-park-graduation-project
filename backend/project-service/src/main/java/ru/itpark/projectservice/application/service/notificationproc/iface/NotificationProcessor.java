package ru.itpark.projectservice.application.service.notificationproc.iface;

import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;

public interface NotificationProcessor {
    public void processNotification(InvitationMessage notification);
}
