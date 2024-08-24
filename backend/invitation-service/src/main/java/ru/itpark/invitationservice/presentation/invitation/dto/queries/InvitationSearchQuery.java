package ru.itpark.invitationservice.presentation.invitation.dto.queries;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.itpark.invitationservice.domain.invitations.Invitation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationSearchQuery implements Specification<Invitation> {

    public String emailTo;
    public String emailFrom;
    public String status;

    @Override
    public Predicate toPredicate(Root<Invitation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction(); // Начальный предикат, представляющий `true`

        if (emailTo != null && !emailTo.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("emailTo"), emailTo));
        }

        if (emailFrom != null && !emailFrom.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(root.get("emailFrom"), emailFrom));
        }

        if (status != null) {
            predicate = cb.and(predicate, cb.equal(root.get("status"), status));
        }

        return predicate;
    }

}
