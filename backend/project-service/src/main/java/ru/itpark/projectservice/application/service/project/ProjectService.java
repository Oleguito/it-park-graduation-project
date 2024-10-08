package ru.itpark.projectservice.application.service.project;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.itpark.projectservice.domain.userproject.UserProject;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.domain.project.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.chatservice.dto.command.CreateChatCommand;
import ru.itpark.projectservice.infrastructure.mapper.ProjectMapper;
import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;
import ru.itpark.projectservice.infrastructure.repositories.project.custom.CustomProjectsRepository;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectResponse;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectsSearchQuery;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Value("${services.chat-service.create-chat-url}")
    private String createChatUrl;
    
    @Autowired
    ProjectRepo projectRepository;
    
    @Autowired
    UserProjectService userProjectService;
    
    RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    CustomProjectsRepository customProjectsRepository;
    @Autowired
    private ProjectMapper projectMapper;

    @Transactional
    public Project save(ProjectCreateCommand projectCreateCommand) {
        
        final Project project = Project.builder()
                          .name(projectCreateCommand.getName())
                          .description(projectCreateCommand.getDescription())
                          .startDate(projectCreateCommand.getStartDate())
                          .endDate(projectCreateCommand.getEndDate())
                          .status(projectCreateCommand.getStatus())
                          .ownerEmail(projectCreateCommand.getOwnerEmail())
                          .dateInfo(projectCreateCommand.getDateInfo())
                          .build();
        
        Project saved = customProjectsRepository.createProject(project);
        
        userProjectService.save(UserProject.builder()
                                .project_id(saved.getId())
                                .email(projectCreateCommand.getCreatorEmail())
                                .build());
        
        CreateChatCommand chatCommand = CreateChatCommand.builder()
                                  .projectName(projectCreateCommand.getName())
                                  .projectId(saved.getId())
                                  .build();
        
        String url = createChatUrl;
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<CreateChatCommand> entity = new HttpEntity<>(chatCommand, headers);
        
        restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
        
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
    
    public List<Project> searchProjects(String nameContains, String descriptionContains, Status status, LocalDateTime startDateFrom, LocalDateTime startDateTo, LocalDateTime endDateFrom, LocalDateTime endDateTo, String ownerEmail) {
        return projectRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseAndStatusAndStartDateBetweenAndEndDateBetweenAndOwnerEmail(nameContains, descriptionContains, status, startDateFrom, startDateTo, endDateFrom, endDateTo, ownerEmail);
    }

    public List<ProjectResponse> findProjects(ProjectsSearchQuery projectsSearchQuery) {
        
        List<Project> findedProjects = projectRepository.findAll(projectsSearchQuery);
        
//        if (findedProjects.isEmpty()) {
//            throw new EntityNotFoundException("Проекты не найдены");
//        }
        
        return projectMapper.toListResponse(findedProjects);
    }
    
    public void removeProject(Long id) {
        projectRepository.deleteById(id);
    }
}
