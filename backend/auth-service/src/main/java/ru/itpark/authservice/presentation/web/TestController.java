package ru.itpark.authservice.presentation.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

//    @PreAuthorize("isAuthenticated()")
    @GetMapping
    @Operation(description = "Тестовый не-jwt контроллер чтобы проверить что приложение отвечает")
    public String test() {
        return "вы авторизованы";
    }
}
