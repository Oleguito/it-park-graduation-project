package ru.itpark.authdto.dto.valueobjects;

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
