package ru.itpark.authservice.application.services;

import org.springframework.stereotype.Service;
import ru.itpark.authservice.domain.user.User;

import java.util.List;

public interface UsersService {

    List<User> getAllUsers();

    User getUserByEmail(String login);
}
