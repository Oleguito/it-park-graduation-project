package ru.itpark.projectservice.application.service.userproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.projectservice.domain.participantproject.UserProject;
import ru.itpark.projectservice.infrastructure.repositories.UserProjectRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    
    private final UserProjectRepo userProjectRepo;
    
    public UserProject save(UserProject userProject){
        
        return userProjectRepo.save(userProject);
    }
    
    public List<UserProject> getAllForProjectId(Long projectId) {
        return userProjectRepo.findByProject_id(projectId);
    }
    
}
