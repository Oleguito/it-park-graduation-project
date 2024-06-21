package ru.itpark.authservice.presentation.web.users.dto.command;

import jakarta.persistence.Column;
import lombok.Getter;
import ru.itpark.authservice.domain.user.valueobjects.Language;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UserCommand {

    String fullName;
    String email;
    String login;
    List<Language> languages;
    String role;
    LocalDateTime createdAt;
    LocalDateTime deletedAt;

}
