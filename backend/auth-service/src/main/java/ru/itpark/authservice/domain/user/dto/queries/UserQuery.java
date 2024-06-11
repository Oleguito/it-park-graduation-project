package ru.itpark.authservice.domain.user.dto.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    public String username;
    public String password;
}
