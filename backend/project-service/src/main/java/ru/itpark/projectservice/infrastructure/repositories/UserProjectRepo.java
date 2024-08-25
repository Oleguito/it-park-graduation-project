package ru.itpark.projectservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itpark.projectservice.domain.participantproject.UserProject;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProjectRepo extends JpaRepository<UserProject, UUID> {
    
    @Query("select u from UserProject u where u.project_id = ?1")
    List<UserProject> findByProject_id(Long project_id);
    
    
}
