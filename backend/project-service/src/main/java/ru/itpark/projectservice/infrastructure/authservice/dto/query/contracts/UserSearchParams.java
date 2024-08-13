package ru.itpark.projectservice.infrastructure.authservice.dto.query.contracts;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itpark.projectservice.infrastructure.authservice.valueobjects.DateInfo;
import ru.itpark.projectservice.infrastructure.authservice.valueobjects.Language;

@Getter
@Setter
@Builder
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
