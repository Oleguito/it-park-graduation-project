package ru.itpark.projectservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.projectservice.domain.participantproject.UserProject;

import java.util.UUID;

@Repository
public interface UserProjectRepo extends JpaRepository<UserProject, UUID> {


}
