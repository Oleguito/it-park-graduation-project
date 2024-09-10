package ru.itpark.documentservice.application.service.documents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.documentservice.domain.documentservice.Document;
import ru.itpark.documentservice.infrastructure.mappers.documents.DocumentsMapper;
import ru.itpark.documentservice.infrastructure.repo.documents.DocumentsRepository;
import ru.itpark.documentservice.presentation.controller.file.dto.queries.DocumentsSearchQuery;
import ru.itpark.documentservice.presentation.controller.file.dto.responses.DocumentResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;
    private final DocumentsMapper documentsMapper;

    public List<DocumentResponse> getLinks(DocumentsSearchQuery documentsSearchQuery) {
        List<Document> findedDocuments = documentsRepository.findAll(documentsSearchQuery);
        return documentsMapper.toListResponse(findedDocuments);
    }

    public void saveFile(Document newFile) {
        documentsRepository.save(newFile);
    }
    
    public Document findByPath(String objectName) {
        return documentsRepository.findByPath(objectName);
    }
    
    public void deleteDocument(Document found) {
        documentsRepository.delete(found);
    }
}
