package ru.itpark.authservice.presentation.web.users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itpark.authservice.application.user.facade.UserCommandFacade;
import ru.itpark.authservice.application.user.facade.UserQueryFacade;
import ru.itpark.authservice.application.user.impl.UsersService;
import ru.itpark.authservice.application.user.query.UserQueryService;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;

    private final UserQueryFacade userQueryFacade;

    private final UserCommandFacade userCommandFacade;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public void createUser(@RequestBody UserCommand userCommand) {
        userCommandFacade.createUser(userCommand);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Получить список всех пользователей микросервиса")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());

    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Залогинить пользователя в keycloak по логину и паролю", description =
            "На этапе создания аннотации возвращает содержимое jwt из keycloak")
    public Map login(@RequestBody UserQuery user) {
        return usersService.login(user);
    }

    @PostMapping("/search")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Найти пользователя по одному или нескольким параметрам", description =
            "Например, те кто владеет английским и учетка создана вчера")
    public List<User> findUser(@RequestBody UserSearchParams userSearchParams) {
        log.info("Searching user with query {}", userSearchParams.getLanguages());
        return userQueryFacade.search(userSearchParams);
    }
}
