package ru.itpark.projectservice.application.service.notificationproc;

import org.springframework.stereotype.Component;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.sharedlib.InvitationMessage;

import java.util.Objects;

@Component("exclude")
public class ExcludeNotificationProcessor implements NotificationProcessor {

    @Override
    public void processNotification(InvitationMessage notification) {
        if (!Objects.equals("exclude", notification.getType())) {
            return;
        }

        String msg = String.format("Привет! Тебя исключили из проекта %s", notification.getProjectTitle());
        notification.setInvitationMessage(msg);
    }

}
