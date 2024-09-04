package ru.itpark.documentservice.infrastructure.mappers.documents;

import org.mapstruct.Mapper;
import ru.itpark.documentservice.domain.documentservice.Document;
import ru.itpark.documentservice.presentation.controller.file.dto.responses.DocumentResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentsMapper {

    DocumentResponse toResponse(Document document);
    List<DocumentResponse> toListResponse(List<Document> documents);

}
