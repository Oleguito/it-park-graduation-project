package ru.itpark.authservice.infrastructure.repositories.user.custom;

import static ru.itpark.authservice.Tables.USERS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.JSONB;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itpark.authservice.domain.user.Role;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.valueobjects.DateInfo;
import ru.itpark.authservice.domain.user.valueobjects.Language;
import ru.itpark.authservice.presentation.web.users.dto.query.contracts.UserSearchParams;
import ru.itpark.authservice.tables.records.UsersRecord;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomUserRepository {

    private final ObjectMapper objectMapper;

    private final DSLContext dsl;

    /*
    *   Does check for existence
    * */
    public User create(
        String fullName,
        String email,
        String login,
        List<Language> languages,
        Role role
    ) throws JsonProcessingException {

        if(dsl.selectFrom(USERS)
                .where(USERS.EMAIL.eq(email))
                .stream().findAny().isPresent()) return null;

        User user = dsl.insertInto(USERS)
                .columns(USERS.FULL_NAME, USERS.EMAIL, USERS.LOGIN, USERS.LANGUAGES, USERS.ROLE, USERS.CREATED_AT, USERS.DELETED_AT)
                .values(
                        fullName,
                        email,
                        login,
                        JSONB.valueOf(objectMapper.writeValueAsString(
                                languages
                        )),
                        role.toString(),
                        LocalDateTime.now(),
                        null
                )
                .returningResult(USERS.ID, USERS.FULL_NAME, USERS.EMAIL, USERS.LOGIN, USERS.LANGUAGES, USERS.ROLE, USERS.CREATED_AT, USERS.DELETED_AT)
                .fetchOne()
                .into(User.class);

        user.setDateInfo(DateInfo.builder()
                .createdAt(LocalDateTime.now())
                .deletedAt(null)
                .build());

        return user;
    }



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
                Condition condition = null;
                for (Language language : searchParams.getLanguages()) {
                    System.out.println("Параметр: " + "%" + objectMapper.writeValueAsString(language) + "%");

                    condition = condition == null ?
//                            USERS.LANGUAGES.cast(String.class).like("%" + objectMapper.writeValueAsString(language) + "%")
                            USERS.LANGUAGES.cast(String.class).like("%" + "{\"level\": \"" + language.getLevel() + "\", \"language\": \"" + language.getLanguage() + "\"}" + "%")
//                            USERS.LANGUAGES.cast(String.class).like("%" + String.valueOf(JSONB.valueOf(objectMapper.writeValueAsString(language))) + "%")
//                            : condition.or(USERS.LANGUAGES.cast(String.class).like("%" + objectMapper.writeValueAsString(language) + "%"));
                            : condition.or(USERS.LANGUAGES.cast(String.class).like("%" + "{\"level\": \"" + language.getLevel() + "\", \"language\": \"" + language.getLanguage() + "\"}" + "%"));
//                            : condition.or(USERS.LANGUAGES.cast(String.class).like("%" + String.valueOf(JSONB.valueOf(objectMapper.writeValueAsString(language))) + "%"));
                }

                query = query.and(condition);

            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error processing JSON", e);
            }
        }

        if (searchParams.getDateInfo() != null && searchParams.getDateInfo().getCreatedAt() != null) {
            query = query.and(USERS.CREATED_AT.eq(searchParams.getDateInfo().getCreatedAt()));
        }

        if (searchParams.getDateInfo() != null && searchParams.getDateInfo().getDeletedAt() != null) {
            query = query.and(USERS.DELETED_AT.eq(searchParams.getDateInfo().getDeletedAt()));
        }

//        SelectLimitPercentStep<UsersRecord> finalQuery = null;
//
//        if (searchParams.getLimit() != null && searchParams.getLimit() > 0) {
//            finalQuery = query.limit(searchParams.getLimit());
//        }
//
//        SelectForUpdateStep<UsersRecord> finalQuery2 = null;
//
//        if (searchParams.getOffset() != null && searchParams.getOffset() >= 0 &&
//                finalQuery != null) {
//            finalQuery2 = finalQuery.offset(searchParams.getOffset());
//        }

        return query.fetchInto(UsersRecord.class).stream()
                .map(record -> record.into(User.class))
                .collect(Collectors.toList());
    }
}