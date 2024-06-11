package ru.itpark.authservice.presentation.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itpark.authservice.application.services.UsersService;
import ru.itpark.authservice.domain.user.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return ResponseEntity.ok(usersService.getUserByEmail(email));
    }
}
