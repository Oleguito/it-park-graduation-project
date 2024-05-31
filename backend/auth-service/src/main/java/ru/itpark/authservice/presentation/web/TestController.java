package ru.itpark.authservice.presentation.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

//    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String test() {
        return "вы авторизованы";
    }
}
