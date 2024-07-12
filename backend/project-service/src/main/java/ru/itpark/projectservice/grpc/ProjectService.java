package ru.itpark.projectservice.grpc;

import com.google.protobuf.Timestamp;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.itpark.projectservice.DateInfo;
import ru.itpark.projectservice.ProjectRequest;
import ru.itpark.projectservice.ProjectResponse;
import ru.itpark.projectservice.ProjectServiceGrpc;
import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.infrastructure.repositories.ProjectRepo;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class ProjectService extends ProjectServiceGrpc.ProjectServiceImplBase {

    private final ProjectRepo projectRepo;

    @Override
    public void getProjectInfo(ProjectRequest request, StreamObserver<ProjectResponse> responseObserver) {

        if (request.getProjectId() == 0) {
            responseObserver.onError(getBadRequestException("Некорректный запрос. Не заполнен Id."));
            return;
        }

        Optional<Project> projectFromDB = projectRepo.findById(request.getProjectId());

        if (projectFromDB.isPresent()) {
            Project project = projectFromDB.get();
            responseObserver.onNext(ProjectResponse.newBuilder()
                    .setId(project.getId())
                    .setName(project.getName())
                    .setStartDate(Timestamp.newBuilder().setNanos(project.getStartDate().getNano()).build())
                    .setEndDate(Timestamp.newBuilder().setNanos(project.getEndDate().getNano()).build())
                    .setStatus(ru.itpark.projectservice.Status.forNumber(project.getStatus().ordinal()))
                    .setOwnerId(project.getOwnerId())
                    .setDateInfo(DateInfo.newBuilder()
                            .setCreatedAt(Timestamp.newBuilder().setNanos(project.getDateInfo().getCreatedAt().getNano()))
                            .setDeletedAt(Timestamp.newBuilder().setNanos(project.getDateInfo().getDeletedAt().getNano()))
                                    .build())
                    .build());
        } else {
            responseObserver.onError(getBadRequestException("Не найден проект с id: " + request.getProjectId()));
        }
        responseObserver.onCompleted();
    }

    private static StatusRuntimeException getBadRequestException(String errorMessage) {
        return Status.INVALID_ARGUMENT
                .withDescription(errorMessage)
                .asRuntimeException();
    }

}
