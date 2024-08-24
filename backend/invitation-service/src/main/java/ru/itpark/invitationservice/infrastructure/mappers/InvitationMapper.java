package ru.itpark.invitationservice.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itpark.invitationservice.domain.invitations.Invitation;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.CreateInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.reponses.InvitationResponse;

import java.util.List;

import static ru.itpark.invitationservice.domain.invitations.VO.enums.Status.SENDED;

@Mapper(componentModel = "spring")
public interface InvitationMapper {

    @Mapping(target = "status", expression = "java(ru.itpark.invitationservice.domain.invitations.VO.enums.Status.SENDED)")
    Invitation toEntity(CreateInvitationCommand command);

    InvitationResponse toResponse(Invitation invitation);

    List<InvitationResponse> toListResponse(List<Invitation> invitations);

}
