package ru.itpark.projectservice.application.service.notificationproc;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itpark.authdto.dto.command.UserCommand;
import ru.itpark.authdto.dto.query.contracts.UserSearchParams;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.sharedlib.InvitationMessage;

import java.util.List;
import java.util.Objects;

@Component("invite")
public class InviteNotificationProcessor implements NotificationProcessor {

    @Value("${services.auth-service.addr}")
    private String authUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void processNotification(InvitationMessage notification) {
        if (!Objects.equals("invite", notification.getType())) {
            return;
        }

        UserSearchParams byEmail = UserSearchParams.builder()
                .email(notification.getInvitedUserEmail())
                .build();

        ResponseEntity<List<UserCommand>> findedUser = restTemplate.exchange(authUrl + "/search",
                HttpMethod.POST,
                new HttpEntity<>(byEmail),
                new ParameterizedTypeReference<List<UserCommand>>() {
                });

        List<UserCommand> body = findedUser.getBody();
        if (body.size() == 0) {
            throw new EntityNotFoundException("Не найден пользователь");
        }

        UserCommand userInfo = body.get(0);

        String msg = String.format("Привет, %s! Тебе приглашение в проект %s от %s",
                userInfo.fullName,
                notification.getProjectTitle(),
                notification.getProjectCreatorEmail());

        notification.setInvitationMessage(msg);

    }

}
