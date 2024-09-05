package ru.itpark.projectservice.application.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.projectservice.domain.userproject.UserProject;
import ru.itpark.projectservice.infrastructure.repositories.UserProjectRepo;
import ru.itpark.projectservice.presentation.userproject.dto.command.delete.UserProjectDeleteCommand;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    
    private final UserProjectRepo userProjectRepo;
    
    public UserProject save(UserProject userProject){
        
        return userProjectRepo.save(userProject);
    }
    
    public List<UserProject> findByEmail(String email) {
    
        return userProjectRepo.findByEmail(email);
    }
    
    public List<UserProject> getAllForProjectId(Long projectId) {
        return userProjectRepo.findByProject_id(projectId);
    }
    
    public void delete(UserProjectDeleteCommand userProjectDeleteCommand) {
        
        userProjectRepo.deleteByEmailAndProjectId(
            userProjectDeleteCommand.getEmail(),
            userProjectDeleteCommand.getProjectId()
        );
    }
    
    public void deleteAllForProjectId(Long id) {
        userProjectRepo.deleteByProject_id(id);
    }
}
