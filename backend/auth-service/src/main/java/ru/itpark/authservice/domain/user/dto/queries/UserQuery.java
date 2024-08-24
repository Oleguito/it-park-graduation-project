package ru.itpark.authservice.domain.user.dto.queries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.authservice.domain.user.valueobjects.DateInfo;
import ru.itpark.authservice.domain.user.valueobjects.Language;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
