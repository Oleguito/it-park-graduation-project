package ru.itpark.authservice.application.user.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itpark.authservice.application.user.query.UserQueryService;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.util.List;
@Slf4j
@Component
@RequiredArgsConstructor
public class UserQueryFacade {

    private final UserQueryService userQueryService;


    @PostMapping("/search")
    public List<User> search(@RequestBody UserSearchParams userSearchParams) {
        return userQueryService.search(userSearchParams);
    }
}
