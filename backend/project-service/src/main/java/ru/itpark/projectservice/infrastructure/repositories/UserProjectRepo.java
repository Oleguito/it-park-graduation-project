package ru.itpark.projectservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.projectservice.domain.userproject.UserProject;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProjectRepo extends JpaRepository<UserProject, UUID> {
    
    @Transactional
    @Modifying
    @Query("delete from UserProject u where u.project_id = ?1")
    int deleteByProject_id(Long project_id);
    
    @Query("select u from UserProject u where u.email = ?1")
    List<UserProject> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("delete from UserProject u where u.email = ?1 and u.project_id = ?2")
    void deleteByEmailAndProjectId(String email, Long project_id);
    
    @Query("select u from UserProject u where u.project_id = ?1")
    List<UserProject> findByProject_id(Long project_id);
    
    
}
