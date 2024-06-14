package ru.itpark.authservice.domain.user.valueobjects;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Language {
    private String language;
    private String level;
}
