package ru.itpark.invitationservice.application.invitations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.invitationservice.domain.invitations.Invitation;
import ru.itpark.invitationservice.infrastructure.mappers.InvitationMapper;
import ru.itpark.invitationservice.infrastructure.repositories.invitations.InvitationsRepo;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.CreateInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.queries.InvitationSearchQuery;
import ru.itpark.invitationservice.presentation.invitation.dto.reponses.InvitationResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationsRepo invitationsRepo;
    private final InvitationMapper invitationMapper;

    public void createInvitation(CreateInvitationCommand createInvitationCommand) {
        Invitation invitation = invitationMapper.toEntity(createInvitationCommand);
        invitationsRepo.save(invitation);
    }

    public List<InvitationResponse> getInvitations(InvitationSearchQuery query) {
        List<Invitation> findedInvitations = invitationsRepo.findAll(query);
        List<InvitationResponse> listResponse = invitationMapper.toListResponse(findedInvitations);
        return listResponse;
    }
}
