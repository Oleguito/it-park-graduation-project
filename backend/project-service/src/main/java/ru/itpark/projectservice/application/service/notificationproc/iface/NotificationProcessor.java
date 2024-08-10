package ru.itpark.projectservice.application.service.notificationproc.iface;

import ru.itpark.sharedlib.InvitationMessage;

public interface NotificationProcessor {
    public void processNotification(InvitationMessage notification);
}
