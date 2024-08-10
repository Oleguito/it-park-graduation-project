package ru.itpark.projectservice.application.service.notificationproc;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.sharedlib.InvitationMessage;

import java.util.Objects;

@Component("invite")
public class InviteNotificationProcessor implements NotificationProcessor {

    @Override
    public void processNotification(InvitationMessage notification) {
        if (!Objects.equals("invite", notification.getType())) {
            return;
        }

        String msg = String.format("Привет! Тебе приглашение в проект %s от %s", notification.getProjectTitle(), notification.getProjectCreatorEmail());
        notification.setInvitationMessage(msg);

    }

}
