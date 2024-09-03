package ru.itpark.projectservice.presentation.projects.dto.query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.itpark.projectservice.domain.project.Project;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectsSearchQuery implements Specification<Project> {

    private String ownerEmail;
    private String projectName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long projectId;

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate = cb.conjunction();

        if (ownerEmail != null && !ownerEmail.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("ownerEmail"), ownerEmail));
        }

        if (projectName != null && !projectName.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("name"), projectName));
        }

        if (startDate != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
        }

        if (endDate != null) {
            predicate = cb.and(predicate, cb.equal(root.get("endDate"), endDate));
        }

        if (projectId != null) {
            predicate = cb.and(predicate, cb.equal(root.get("id"), projectId));
        }

        return predicate;

    }
}
