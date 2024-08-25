package ru.itpark.projectservice.presentation.projects;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.itpark.projectservice.application.service.KafkaService;
import ru.itpark.projectservice.application.service.ProjectService;
import ru.itpark.projectservice.application.service.userproject.UserProjectService;
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

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectsController {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    UserProjectService userProjectService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    KafkaService kafkaService;

    @RequestMapping("/alltest")
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
                .ownerId(987564321L)
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
    public void sendNotification(@RequestBody InvitationMessage notificationMessage) {

        kafkaService.sendNotificationMessage("notification-topic", notificationMessage);
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
