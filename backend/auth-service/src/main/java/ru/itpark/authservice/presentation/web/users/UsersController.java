package ru.itpark.authservice.presentation.web.users;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itpark.authservice.application.user.facade.UserCommandFacade;
import ru.itpark.authservice.application.user.facade.UserQueryFacade;
import ru.itpark.authservice.application.user.impl.UsersService;
import ru.itpark.authservice.application.user.mapper.UserMapper;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.presentation.web.users.dto.command.UserCommand;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", exposedHeaders = "*", maxAge = 3600)
public class UsersController {

    private final UsersService usersService;

    private final UserQueryFacade userQueryFacade;

    private final UserCommandFacade userCommandFacade;

    private final UserMapper userMapper;

    @RequestMapping(("/create"))
    @PreAuthorize("isAuthenticated() && #userCommand.email == authentication.principal.claims['email']")
    @CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE }, allowedHeaders = "*", exposedHeaders = "*", maxAge = 3600)
    public ResponseEntity<UserQuery> createUser(@RequestBody UserCommand userCommand) throws Exception {

        final List<User> foundUsers = userQueryFacade.search(
                UserSearchParams.builder()
                        .email(userCommand.getEmail())
                        .build()
        );

        if(foundUsers.size() > 1) throw new RuntimeException(
                "Существует несколько пользователей с таким адресом электронной почты");

        if(foundUsers.size() == 0) {

            var user = userCommandFacade.createUser(userCommand);
            System.out.println("user: " + user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userMapper.toQuery(
                            user
                    ));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(null);
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Найти пользователя по одному или нескольким параметрам", description =
            "Например, те кто владеет английским и учетка создана вчера")
    @CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*", methods = {RequestMethod.POST})
    public List<User> findUser(@RequestBody UserSearchParams userSearchParams) {
        log.info("Searching user with query {}", userSearchParams.getLanguages());
        return userQueryFacade.search(userSearchParams);
    }



}
