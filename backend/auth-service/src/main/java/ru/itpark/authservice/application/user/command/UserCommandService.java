package ru.itpark.authservice.application.user.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.Role;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.converters.LanguageConverter;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
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
                Role.valueOf(userCommand.getRole())
        );
    }
}
