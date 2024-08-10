package ru.itpark.authdto.dto.query.contracts;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itpark.authdto.dto.valueobjects.DateInfo;
import ru.itpark.authdto.dto.valueobjects.Language;

import java.util.List;

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
