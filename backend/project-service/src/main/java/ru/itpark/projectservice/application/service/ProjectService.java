package ru.itpark.projectservice.application.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itpark.projectservice.domain.participantproject.UserProject;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.domain.project.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.authservice.dto.query.UserQuery;
import ru.itpark.projectservice.infrastructure.authservice.dto.query.contracts.UserSearchParams;
import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;
import ru.itpark.projectservice.infrastructure.repositories.project.custom.CustomProjectsRepository;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService {
    
    @Autowired
    ProjectRepo projectRepository;

//    @Autowired
//    SslBundles sslBundles;

//    @Autowired
//    ProjectMapper projectMapper;
    
    // RestClient restClient;
    
    RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    CustomProjectsRepository customProjectsRepository;
    
    public Project save(ProjectCreateCommand projectCreateCommand) {
        
        final Set<UserProject> participants = new HashSet<>();
        // "https://localhost:8088/api/users"
        
        
        // получить пользователей по email из user-service
        List<UserQuery> users = new ArrayList<>();
        Arrays.stream(projectCreateCommand.getParticipantsEmails()).forEach(
        email -> {
            String url = "https://localhost:8088/api/users/search";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            UserSearchParams searchParams = UserSearchParams.builder()
                                            .email(email)
                                            .build();
            
            HttpEntity<UserSearchParams> requestEntity = new HttpEntity<>(searchParams, headers);
            
            ResponseEntity<UserQuery> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            UserQuery.class
            );
            
            UserQuery response = responseEntity.getBody();
            users.add(response);
        }
        );
        
        System.out.println();
        
//        Arrays.stream(projectCreateCommand.getParticipantsEmails()).forEach(email -> {
//            participants.add(
//            UserProject.builder()
//            .id(ParticipantProjectId.builder()
//
//                .build())
//            .build()
//            );
//        });
        
        final Project project = Project.builder()
                                .name(projectCreateCommand.getName())
                                .description(projectCreateCommand.getDescription())
                                .startDate(projectCreateCommand.getStartDate())
                                .endDate(projectCreateCommand.getEndDate())
                                .status(projectCreateCommand.getStatus())
                                .ownerId(projectCreateCommand.getOwnerId())
                                .dateInfo(projectCreateCommand.getDateInfo())
//                                .participants(participants)
                                .build();
        
        return customProjectsRepository.createProject(project);
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
