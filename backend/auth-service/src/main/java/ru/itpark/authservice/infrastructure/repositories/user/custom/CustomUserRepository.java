package ru.itpark.authservice.infrastructure.repositories.user.custom;

import org.springframework.stereotype.Repository;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.util.List;

@Repository
public class CustomUserRepository {

    // Тут будет JOOQ для поиска пользователя по параметрам
    public List<User> search(UserSearchParams searchParams) {
        return null;
    }
}
