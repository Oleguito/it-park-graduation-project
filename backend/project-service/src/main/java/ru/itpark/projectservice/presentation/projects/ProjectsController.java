package ru.itpark.projectservice.presentation.projects;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.itpark.projectservice.application.service.KafkaService;
import ru.itpark.projectservice.application.service.ProjectService;
import ru.itpark.projectservice.application.service.userproject.UserProjectService;
import ru.itpark.projectservice.infrastructure.exceptions.ApiError;
import ru.itpark.projectservice.infrastructure.exceptions.exceptions.UserWasAlreadyInvitedException;
import ru.itpark.projectservice.infrastructure.invitationservice.invitation.dto.command.CreateInvitationCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectsSearchQuery;
import ru.itpark.projectservice.presentation.userproject.dto.command.create.UserProjectCreateCommand;
import ru.itpark.projectservice.domain.UserResponse;
import ru.itpark.projectservice.domain.userproject.UserProject;
import ru.itpark.projectservice.domain.project.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.project.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;
import ru.itpark.projectservice.infrastructure.mapper.ProjectMapper;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectResponse;
import ru.itpark.projectservice.presentation.userproject.dto.command.delete.UserProjectDeleteCommand;
import ru.itpark.projectservice.infrastructure.invitationservice.invitationstatus.InvitationStatus;
import com.nimbusds.jose.shaded.gson.Gson;

@RestController
@RequestMapping(value = "/projects")
@CrossOrigin(origins = "*")
public class ProjectsController {

    @Value("${invitation-service.user-already-invited-error-message}")
    private String userAlreadyInvitedErrorMessage;
    
    @Value("${invitation-service.create-invitation}")
    private String createInvitationUrl;
    
    @Autowired
    ProjectService projectService;
    
    @Autowired
    UserProjectService userProjectService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    KafkaService kafkaService;
    
    RestTemplate restTemplate = new RestTemplate();
    
    Gson gson = new Gson();
    
    @RequestMapping(value = "/alltest")
    public List<ProjectResponse> getAllTest() {
        return List.of(
            ProjectResponse.builder()
                .id(123456789L)
                .name("project name")
                .description("project description\n Этот мок проект реально пришел из сервера")
                .status(Status.NEW)
                .dateInfo(DateInfo.builder()
                                .createdAt(LocalDateTime.now())
                        .build())
                .endDate(LocalDateTime.now().plusDays(3L))
                .ownerEmail("bla@gmail.com")
            .build()
        );
    }

    @PostMapping("/add")
    public ProjectResponse createProject(@RequestBody ProjectCreateCommand projectCreateCommand) {

        return projectMapper.toResponse(
                projectService.save(projectCreateCommand)
        );
    }

    @GetMapping("/all")
    public List<ProjectResponse> getAll() {

        return projectMapper.toListResponse(projectService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/find")
    @ResponseBody
    @CrossOrigin
    public List<ProjectResponse> getProjectsByFilter(@RequestBody ProjectsSearchQuery projectsSearchQuery) {
        return projectService.findProjects(projectsSearchQuery);
    }
    
    
    /**
     *
     * @param projectId Идентификатор проекта, для которого нужно
     *                  найти список список объектов, содержащих
     *                  электронные адреса участников
     * @return список объектов, содержащих электронные адреса участников
     */
    @GetMapping("/all/{projectId}")
    public List<UserResponse> getUsersForProjectId(@PathVariable Long projectId) {
        
        return userProjectService.getAllForProjectId(projectId)
               .stream()
               .map(userProject -> UserResponse.builder()
                                   .email(userProject.getEmail())
                                   .build())
               .toList();
    }
    
    @RequestMapping(value = "/message", method=RequestMethod.POST)
    @CrossOrigin
    // @Transactional
    public void sendNotification(@RequestBody InvitationMessage notificationMessage) {

        kafkaService.sendNotificationMessage("notification-topic", notificationMessage);
        
        
        System.out.println("url: " + createInvitationUrl);
        
        // Create the request body (CreateInvitationCommand)
        CreateInvitationCommand command = CreateInvitationCommand.builder()
                          .emailTo(notificationMessage.getInvitedUserEmail())
                          .emailFrom(notificationMessage.getProjectCreatorEmail())
                          .projectId(notificationMessage.getProjectId())
                          .invitationStatus(InvitationStatus.SENT)
                          .type(notificationMessage.getType())
                          .invitationMessage(notificationMessage.getInvitationMessage())
                          .build();
        
        System.out.println("command: " + command);
        
        // Create the request entity
        HttpEntity<CreateInvitationCommand> requestEntity = new HttpEntity<>(command);
    
        // Make the POST request using exchange()
        ResponseEntity<Void> responseEntity;
        try {
            responseEntity= restTemplate.exchange(
                createInvitationUrl,
                HttpMethod.POST,
                requestEntity,
                Void.class
            );
        } catch (RestClientException e) {
            
            final var json = e.getMessage()
                             .substring(0, e.getMessage().length() - 1)
                             .substring(7);
            final ApiError parsed = gson.fromJson(json,ApiError.class);
            
            if(parsed.getMessage().contains(userAlreadyInvitedErrorMessage)) {
                throw new UserWasAlreadyInvitedException("Пользователя уже пригласили");
            }
            
            throw new RuntimeException(e);
        }
        
        System.out.println();
    }
    
    @RequestMapping(path = "/add-participant", method = RequestMethod.POST)
    @CrossOrigin
    public void addParticipant(@RequestBody UserProjectCreateCommand userProjectCreateCommand) {

        userProjectService.save(UserProject.builder()
                                .email(userProjectCreateCommand.getEmail())
                                .project_id(userProjectCreateCommand.getProjectId())
                                .build());
    }
    
    @RequestMapping(path = "/remove-participant", method = RequestMethod.POST)
    @CrossOrigin
    public void removeParticipant(@RequestBody UserProjectDeleteCommand userProjectDeleteCommand) {
        
        userProjectService.delete(
            userProjectDeleteCommand
        );
    }
}
