package ru.itpark.projectservice.infrastructure.authservice.valueobjects;

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
