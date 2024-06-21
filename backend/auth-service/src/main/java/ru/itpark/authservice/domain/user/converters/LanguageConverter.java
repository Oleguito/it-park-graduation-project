package ru.itpark.authservice.domain.user.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;
import ru.itpark.authservice.domain.user.valueobjects.Language;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;


@Converter(autoApply = true)
@Component
public class LanguageConverter implements AttributeConverter<List<Language>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Language> languages) {
        try {
            return objectMapper.writeValueAsString(languages);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting list of languages to JSON", e);
        }
    }

    @Override
    public List<Language> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Language.class));
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to list of languages", e);
        }
    }
}