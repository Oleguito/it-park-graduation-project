package ru.itpark.projectservice.infrastructure.authservice.dto.query;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.projectservice.infrastructure.authservice.valueobjects.DateInfo;
import ru.itpark.projectservice.infrastructure.authservice.valueobjects.Language;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private Long id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String login;
    private List<Language> languages;
    private String role;
    private DateInfo dateInfo;
}
