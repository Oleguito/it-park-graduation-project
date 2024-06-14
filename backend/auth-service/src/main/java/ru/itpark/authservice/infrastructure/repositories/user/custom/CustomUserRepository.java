package ru.itpark.authservice.infrastructure.repositories.user.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.SelectForUpdateStep;
import org.jooq.SelectLimitPercentStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;
import ru.itpark.authservice.tables.records.UsersRecord;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itpark.authservice.tables.Users.USERS;

@Repository
@RequiredArgsConstructor
public class CustomUserRepository {

    private final ObjectMapper objectMapper;

    private final DSLContext dsl;

    public List<User> search(UserSearchParams searchParams) {

        // Returns autocloseable
        var query = dsl.selectFrom(USERS).where(DSL.noCondition());

        if (searchParams.getId() != null) {
            query = query.and(USERS.ID.eq(searchParams.getId()));
        }

        if (searchParams.getLogin() != null) {
            query = query.and(USERS.LOGIN.eq(searchParams.getLogin()));
        }

        if (searchParams.getEmail() != null) {
            query = query.and(USERS.EMAIL.eq(searchParams.getEmail()));
        }

        if (searchParams.getLanguages() != null) {
            try {
                String languagesJson = objectMapper.writeValueAsString(searchParams.getLanguages());
                query = query.and(USERS.LANGUAGES.contains(JSONB.valueOf(languagesJson)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error processing JSON", e);
            }
        }

        if (searchParams.getDateInfo().getCreatedAt() != null) {
            query = query.and(USERS.CREATED_AT.eq(searchParams.getDateInfo().getCreatedAt()));
        }

        if (searchParams.getDateInfo().getDeletedAt() != null) {
            query = query.and(USERS.DELETED_AT.eq(searchParams.getDateInfo().getDeletedAt()));
        }

        SelectLimitPercentStep<UsersRecord> finalQuery = null;

        if (searchParams.getLimit() != null && searchParams.getLimit() > 0) {
            finalQuery = query.limit(searchParams.getLimit());
        }

        SelectForUpdateStep<UsersRecord> finalQuery2 = null;

        if (searchParams.getOffset() != null && searchParams.getOffset() >= 0 &&
                finalQuery != null) {
            finalQuery2 = finalQuery.offset(searchParams.getOffset());
        }

        return finalQuery2.fetchInto(UsersRecord.class).stream()
                .map(record -> record.into(User.class))
                .collect(Collectors.toList());
    }
}