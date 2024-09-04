package ru.itpark.documentservice.presentation.controller.file.dto.queries;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.itpark.documentservice.domain.documentservice.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentsSearchQuery implements Specification<Document> {

    private Long projectId;
    private Long userId;

    @Override
    public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate = cb.conjunction();

        if (projectId != null) {
            predicate = cb.and(predicate, cb.equal(root.get("projectId"), projectId));
        }

        if (userId != null) {
            predicate = cb.and(predicate, cb.equal(root.get("userId"), userId));
        }

        return predicate;
    }
}
