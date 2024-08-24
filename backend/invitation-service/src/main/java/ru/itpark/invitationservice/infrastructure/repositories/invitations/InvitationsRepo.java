package ru.itpark.invitationservice.infrastructure.repositories.invitations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itpark.invitationservice.domain.invitations.Invitation;

@Repository
public interface InvitationsRepo extends JpaRepository<Invitation, Long>, JpaSpecificationExecutor<Invitation> {
}
