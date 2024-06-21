package ru.itpark.authservice.application.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.user.command.UserCommandService;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

@Service
@RequiredArgsConstructor
public class UserCommandFacade {

    private final UserCommandService userCommandService;

    private final UserMapper userMapper;

    public void createUser(UserCommand userCommand) {
        userCommandService.createUser(userCommand);
    }
}
