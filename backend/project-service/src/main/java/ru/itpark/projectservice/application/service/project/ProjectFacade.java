package ru.itpark.projectservice.application.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.projectservice.domain.userproject.UserProject;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectResponse;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectsSearchQuery;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectFacade {
    
    private final ProjectService projectService;
    
    private final UserProjectService userProjectService;
    
    
    public List<ProjectResponse> findByEmail(String email) {
        
        List<UserProject> userProjects = userProjectService.findByEmail(email);
        
        if(userProjects.isEmpty()) return List.of();
        
        final var result =  userProjects.stream()
                            .map(userProject -> {
                                return userProject.getProject_id();
                            })
                            .collect(Collectors.toList());
        
        
        ProjectsSearchQuery built = ProjectsSearchQuery.builder()
                                    .projectIds(result)
                                    .build();
        
        
        
        return projectService.findProjects(built);
    }
    
    @Transactional
    public void removeProject(Long id) {
        projectService.removeProject(id);
        userProjectService.deleteAllForProjectId(id);
    }
}
