package ru.itpark.projectservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.projectservice.domain.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {

}
