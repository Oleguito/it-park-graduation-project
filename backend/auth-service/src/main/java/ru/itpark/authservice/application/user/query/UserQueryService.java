package ru.itpark.authservice.application.user.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.infrastructure.repositories.user.custom.CustomUserRepository;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;

import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    private final CustomUserRepository customUserRepository;

    public UserQueryService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public List<User> search(UserSearchParams searchParams) {
        return customUserRepository.search(searchParams);
    }
}
