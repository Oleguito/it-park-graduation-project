package ru.itpark.authservice.application.services;

import org.springframework.stereotype.Service;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    User getUserByEmail(String login);

    String login(UserQuery user);
}
