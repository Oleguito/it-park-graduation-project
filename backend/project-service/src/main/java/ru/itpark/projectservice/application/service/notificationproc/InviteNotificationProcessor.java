package ru.itpark.projectservice.application.service.notificationproc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.errors.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import ru.itpark.projectservice.application.service.notificationproc.iface.NotificationProcessor;
import ru.itpark.projectservice.infrastructure.authservice.dto.command.UserCommand;
import ru.itpark.projectservice.infrastructure.authservice.dto.query.contracts.UserSearchParams;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;

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

        HttpServletRequest req = null;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            req = attributes.getRequest();
        }

        String authorization = req.getHeader("Authorization");

        if (!authorization.startsWith("Bearer ")) {
            throw new AuthenticationException("Authorization header is incorrect");
        }

        UserSearchParams byEmail = UserSearchParams.builder()
                .email(notification.getInvitedUserEmail())
                .build();

        List<String> header = List.of(authorization);

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Authorization", header);

        MultiValueMap<String, String > headersToRequest = new MultiValueMapAdapter<>(headers);

        ResponseEntity<List<UserCommand>> findedUser = restTemplate.exchange(authUrl + "/api/users/search",
                HttpMethod.POST,
                new HttpEntity<>(byEmail, headersToRequest),
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
