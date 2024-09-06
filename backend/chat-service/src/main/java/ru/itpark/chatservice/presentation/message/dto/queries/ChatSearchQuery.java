package ru.itpark.chatservice.presentation.message.dto.queries;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.itpark.chatservice.domain.chat.Chat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatSearchQuery implements Specification<Chat> {

    private Long projectId;
    private List<Long> projectIds;
    private Long chatId;
    private List<Long> chatIds;

    @Override
    public Predicate toPredicate(Root<Chat> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();

        if (projectId != null) {
            predicate = cb.and(predicate, cb.equal(root.get("projectId"), projectId));
        }

        if (projectIds != null && projectIds.size() > 0) {
            predicate = cb.and(predicate, root.get("projectId").in(projectIds));
        }

        if (chatId != null) {
            cb.and(predicate, cb.equal(root.get("id"), chatId));
        }

        if (chatIds != null && chatIds.size() > 0) {
            predicate = cb.and(predicate, root.get("id").in(chatIds));
        }

        return predicate;
    }
}
