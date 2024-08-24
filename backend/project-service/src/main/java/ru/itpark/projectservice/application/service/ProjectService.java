package ru.itpark.projectservice.application.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itpark.projectservice.application.service.userproject.UserProjectService;
import ru.itpark.projectservice.domain.participantproject.UserProject;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.domain.project.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;
import ru.itpark.projectservice.infrastructure.repositories.project.custom.CustomProjectsRepository;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    
    @Autowired
    ProjectRepo projectRepository;
    
    @Autowired
    UserProjectService userProjectService;
    
    // RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    CustomProjectsRepository customProjectsRepository;
    
    public Project save(ProjectCreateCommand projectCreateCommand) {
        
        final Project project = Project.builder()
                          .name(projectCreateCommand.getName())
                          .description(projectCreateCommand.getDescription())
                          .startDate(projectCreateCommand.getStartDate())
                          .endDate(projectCreateCommand.getEndDate())
                          .status(projectCreateCommand.getStatus())
                          .ownerId(projectCreateCommand.getOwnerId())
                          .dateInfo(projectCreateCommand.getDateInfo())
                          .build();
        
        Project saved = customProjectsRepository.createProject(project);
        
        userProjectService.save(UserProject.builder()
                                .project_id(saved.getId())
                                .email(projectCreateCommand.getCreatorEmail())
                                .build());
        
        return saved;
    }
    
    public List<Project> getAll() {
        return projectRepository.findAll();
    }
    
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> {
            return new EntityNotFoundException("Проект с таким id не найден");
        });
    }
    
    public Project findByName(String name) {
        return projectRepository.findByName(name).orElseThrow(() -> {
            return new EntityNotFoundException("Проект с таким названием не найден");
        });
    }
    
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
    
    public List<Project> searchProjects(String nameContains, String descriptionContains, Status status, LocalDateTime startDateFrom, LocalDateTime startDateTo, LocalDateTime endDateFrom, LocalDateTime endDateTo, Long ownerId) {
        return projectRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndStatusAndStartDateBetweenAndEndDateBetweenAndOwnerId(nameContains, descriptionContains, status, startDateFrom, startDateTo, endDateFrom, endDateTo, ownerId);
    }
}
