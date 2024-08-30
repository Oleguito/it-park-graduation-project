package ru.itpark.invitationservice.infrastructure.repositories.invitations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itpark.invitationservice.domain.invitations.Invitation;

@Repository
public interface InvitationsRepo extends JpaRepository<Invitation, Long>, JpaSpecificationExecutor<Invitation> {
    
    @Query("select (count(i) > 0) from Invitation i where i.emailTo = ?1 and i.projectId = ?2")
    boolean existsByEmailToAndProjectId(String emailTo, Long projectId);
    
}
