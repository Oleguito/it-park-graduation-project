package ru.itpark.authservice.presentation.web.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpark.authservice.application.user.impl.UsersService;
import ru.itpark.authservice.application.user.query.UserQueryService;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.dto.queries.UserQuery;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final UserQueryService userQueryService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return ResponseEntity.ok(usersService.getUserByEmail(email));
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserQuery user) {
        return usersService.login(user);
    }

    @PostMapping("/search")
    public List<User> findUser(@RequestBody UserSearchParams userSearchParams) {
        log.info("Searching user with query {}", userSearchParams.getLanguages());
        return userQueryService.search(userSearchParams);
    }

}
