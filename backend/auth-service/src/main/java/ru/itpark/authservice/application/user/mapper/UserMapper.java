package ru.itpark.authservice.application.user.mapper;

import org.mapstruct.Mapper;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;

@Mapper(componentModel = "spring")
public interface UserMapper{

    User toUser(UserCommand userCommand);

    UserQuery toQuery(User user);
}
