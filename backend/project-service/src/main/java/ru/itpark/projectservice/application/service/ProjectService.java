package ru.itpark.projectservice.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.domain.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;
import ru.itpark.projectservice.infrastructure.repositories.project.custom.CustomProjectsRepository;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepository;

    @Autowired
    CustomProjectsRepository customProjectsRepository;

    public Project save (ProjectCreateCommand projectCreateCommand) {
        return customProjectsRepository.createProject(projectCreateCommand);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException(
                    "Проект с таким id не найден"
            );
        });
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name).orElseThrow(() -> {
            return new EntityNotFoundException(
                    "Проект с таким названием не найден"
            );
        });
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> searchProjects(
            String nameContains,
            String descriptionContains,
            Status status,
            LocalDateTime startDateFrom,
            LocalDateTime startDateTo,
            LocalDateTime endDateFrom,
            LocalDateTime endDateTo,
            Long ownerId
    ) {
        return projectRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndStatusAndStartDateBetweenAndEndDateBetweenAndOwnerId(
                nameContains,
                descriptionContains,
                status,
                startDateFrom,
                startDateTo,
                endDateFrom,
                endDateTo,
                ownerId
        );
    }
}
