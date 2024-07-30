package ru.itpark.projectservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.domain.valueobjects.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    List<Project> findAll();

    Optional<Project> findById(Long id);

    Optional<Project> findByName(String name);

    Project save(Project project);

    void deleteById(Long id);

    List<Project> findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndStatusAndStartDateBetweenAndEndDateBetweenAndOwnerId(
            String nameContains,
            String descriptionContains,
            Status status,
            LocalDateTime startDateFrom,
            LocalDateTime startDateTo,
            LocalDateTime endDateFrom,
            LocalDateTime endDateTo,
            Long ownerId
    );


}
