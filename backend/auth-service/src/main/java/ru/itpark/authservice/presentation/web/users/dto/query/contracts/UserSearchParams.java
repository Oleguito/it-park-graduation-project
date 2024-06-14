package ru.itpark.authservice.presentation.web.users.dto.query.contracts;

import ru.itpark.authservice.domain.user.valueobjects.DateInfo;
import ru.itpark.authservice.domain.user.valueobjects.Language;

import java.util.List;

public class UserSearchParams {
    Long id;
    String fullName;
    String email;
    String login;
    List<Language> languages;
    DateInfo dateInfo;
    Integer limit = 15;
    Integer offset = 0;
}
