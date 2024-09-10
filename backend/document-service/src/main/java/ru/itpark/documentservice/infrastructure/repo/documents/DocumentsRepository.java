package ru.itpark.documentservice.infrastructure.repo.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itpark.documentservice.domain.documentservice.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {
    @Query("select d from Document d where d.fileName = ?1")
    Document findByPath(String fileName);
}
