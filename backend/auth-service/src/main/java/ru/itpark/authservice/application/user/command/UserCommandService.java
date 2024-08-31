package ru.itpark.authservice.application.user.command;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.Role;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.converters.LanguageConverter;
import ru.itpark.authservice.infrastructure.repositories.user.UserRepository;
import ru.itpark.authservice.infrastructure.repositories.user.custom.CustomUserRepository;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final LanguageConverter languageConverter;

    private final CustomUserRepository customUserRepository;

    public User createUser(UserCommand userCommand) throws JsonProcessingException {
        return customUserRepository.create(
                userCommand.getFullName(),
                userCommand.getEmail(),
                userCommand.getLogin(),
                userCommand.getLanguages(),
                Role.valueOf(Strings.toRootUpperCase(userCommand.getRole()))
        );
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
