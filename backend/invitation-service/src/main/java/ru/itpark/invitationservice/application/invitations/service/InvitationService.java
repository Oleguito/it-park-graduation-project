package ru.itpark.invitationservice.application.invitations.service;

import com.sun.jdi.VoidType;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.itpark.invitationservice.domain.invitations.Invitation;
import ru.itpark.invitationservice.domain.invitations.VO.enums.Status;
import ru.itpark.invitationservice.infrastructure.mappers.InvitationMapper;
import ru.itpark.invitationservice.infrastructure.projectservice.UserProjectCreateCommand;
import ru.itpark.invitationservice.infrastructure.repositories.invitations.InvitationsRepo;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.DeleteInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.SetInvitationStatusCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.CreateInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.queries.InvitationSearchQuery;
import ru.itpark.invitationservice.presentation.invitation.dto.reponses.InvitationResponse;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationsRepo invitationsRepo;
    private final InvitationMapper invitationMapper;
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${services.project-service.url}")
    private String projectServiceURL;

    public void createInvitation(CreateInvitationCommand createInvitationCommand) {
        Invitation invitation = invitationMapper.toEntity(createInvitationCommand);
        invitationsRepo.save(invitation);
    }

    public List<InvitationResponse> getInvitations(InvitationSearchQuery query) {
        List<Invitation> findedInvitations = invitationsRepo.findAll(query);
        List<InvitationResponse> listResponse = invitationMapper.toListResponse(findedInvitations);
        return listResponse;
    }

    @Transactional
    public void setInvitationStatus(SetInvitationStatusCommand invitationStatusCommand) {

        InvitationSearchQuery criteria = InvitationSearchQuery.builder()
                .invUUID(UUID.fromString(invitationStatusCommand.getInvUUID()))
                .build();

        List<Invitation> findedInvitation = invitationsRepo.findAll(criteria);

        if (findedInvitation.isEmpty()) {
            throw new EntityNotFoundException("Не найдено уведомление");
        }

        Invitation invitation = findedInvitation.get(0);
        invitation.setStatus(Status.valueOf(invitationStatusCommand.getStatus()));

        invitationsRepo.save(invitation);

//        throw new EntityNotFoundException("DSADASDASDA");

        // TODO: дернуть ручку в project-service для включения человека в проект

        if (Objects.equals(invitationStatusCommand.getStatus(), "ACCEPTED")) {

            UserProjectCreateCommand userProjectCreateCommand = UserProjectCreateCommand
                    .builder()
                    .email(invitation.getEmailTo())
                    .projectId(invitation.getProjectId())
                    .build();

            HttpEntity<UserProjectCreateCommand> userProjectCreateCommandHttpEntity = new HttpEntity<>(userProjectCreateCommand);

            ResponseEntity<Void> exchange = restTemplate.exchange(projectServiceURL + "/projects/add-participant",
                    HttpMethod.POST,
                    userProjectCreateCommandHttpEntity,
                    Void.class);

            if (exchange.getStatusCode() != HttpStatus.OK) {
                throw new EntityNotFoundException("Не удалось включить участника в проект.");
            }

        }
    }

    public void deleteInvitation(DeleteInvitationCommand deleteInvitationCommand) {
        InvitationSearchQuery searchQuery = getSearchQuery(deleteInvitationCommand.getInvUUID());
        invitationsRepo.delete(searchQuery);
    }

    private InvitationSearchQuery getSearchQuery(String invUUID) {
        return InvitationSearchQuery
                .builder()
                .invUUID(UUID.fromString(invUUID))
                .build();
    }

}
