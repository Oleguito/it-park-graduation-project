package ru.itpark.authservice.presentation.web.users.dto.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.*;
import ru.itpark.authservice.domain.user.valueobjects.Language;

import java.time.LocalDateTime;
import java.util.List;

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
