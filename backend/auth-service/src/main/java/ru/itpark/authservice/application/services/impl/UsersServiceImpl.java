package ru.itpark.authservice.application.services.impl;

import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.services.UsersService;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.infrastructure.repositories.UserRepository;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }
}
