package ru.itpark.authservice.application.user.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.converters.LanguageConverter;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.infrastructure.repositories.user.UserRepository;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final LanguageConverter languageConverter;


    public void createUser(UserCommand userCommand) {
        userRepository.saveUser(
                userCommand.getFullName(),
                userCommand.getEmail(),
                userCommand.getLogin(),
                languageConverter.convertToDatabaseColumn(userCommand.getLanguages()),
                userCommand.getRole(),
                userCommand.getCreatedAt(),
                userCommand.getDeletedAt()
        );
    }
}
