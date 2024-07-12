package ru.itpark.authservice.application.grpc;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.itpark.authservice.ProjectRequest;
import ru.itpark.authservice.ProjectResponse;
import ru.itpark.authservice.ProjectServiceGrpc;

@Service
@Slf4j
public class ProjectServiceGrpcImpl {

    @GrpcClient("GLOBAL")
    private ProjectServiceGrpc.ProjectServiceBlockingStub stub;

    public ProjectResponse getProject(Long projectId) {
        ProjectResponse projectInfo = stub.getProjectInfo(buildProject(projectId));
        return projectInfo;
    }

    private static ProjectRequest buildProject(Long projectId) {
        return ProjectRequest.newBuilder()
                .setProjectId(projectId)
                .build();
    }

//    @PostConstruct
//    public void getProject() {
//        log.error("Response from GRPC Server: {} ", getProject(1L));
//    }

}
