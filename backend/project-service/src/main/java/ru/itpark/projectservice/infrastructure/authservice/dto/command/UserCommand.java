package ru.itpark.projectservice.infrastructure.authservice.dto.command;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.projectservice.infrastructure.authservice.valueobjects.Language;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {

    public String fullName;
    public String email;
    public String login;
    public List<Language> languages;
    public String role;
    @JsonIgnore
    public LocalDateTime createdAt;
    @JsonIgnore
    public LocalDateTime deletedAt;

}
