package ru.itpark.authservice.application.services.impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.services.UsersService;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.infrastructure.repositories.UserRepository;
import ru.itpark.authservice.infrastructure.config.security.keycloak.KeycloakClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Value("${keycloak.loginUrl}")
    private String loginUrl;

    private final KeycloakClient keycloakClient;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }

    @Override
    public String login(UserQuery user) {
        Map keycloakResponse = keycloakClient.getUserInfo(user);
        log.info("Response info: {}", keycloakResponse);
        return (String) keycloakResponse.get("access_token");
    }
}
