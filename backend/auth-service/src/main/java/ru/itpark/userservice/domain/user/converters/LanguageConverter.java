package ru.itpark.userservice.domain.user.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import org.hibernate.type.internal.ParameterizedTypeImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import ru.itpark.userservice.domain.user.VO.Language;

import java.lang.reflect.Type;
import java.util.List;


@Component
public class LanguageConverter implements AttributeConverter<List<Language>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Language> o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Не удалось преобразовать объект в строку. " + o);
        }
    }

    @Override
    public List<Language> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Language>>() {
                @Override
                public Type getType() {
                    TypeToken<List<Language>> typeToken = new TypeToken<List<Language>>() {
                    };

                    return typeToken.getType();
                }
            });
        } catch (JsonProcessingException exception) {
            throw new IllegalArgumentException("Не удалось преобразовать строку в объект. " + dbData);
        }
    }
}
