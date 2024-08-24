package ru.itpark.projectservice.application.service.userproject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.projectservice.domain.participantproject.UserProject;
import ru.itpark.projectservice.infrastructure.repositories.UserProjectRepo;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    
    private final UserProjectRepo userProjectRepo;
    
    public UserProject save(UserProject userProject){
        
        return userProjectRepo.save(userProject);
    }
}
