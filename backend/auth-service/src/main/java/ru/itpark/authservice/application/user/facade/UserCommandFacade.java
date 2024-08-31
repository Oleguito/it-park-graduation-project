package ru.itpark.authservice.application.user.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.application.user.command.UserCommandService;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

@Service
@RequiredArgsConstructor
public class UserCommandFacade {

    private final UserCommandService userCommandService;

    private final UserMapper userMapper;

    public User createUser(UserCommand userCommand) throws JsonProcessingException {
        return userCommandService.createUser(userCommand);
    }

    public boolean userExists(String email) {
        return userCommandService.userExists(email);
    }

}
