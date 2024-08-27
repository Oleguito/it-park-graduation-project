package ru.itpark.invitationservice.presentation.invitation.dto.reponses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.itpark.invitationservice.domain.invitations.VO.enums.Status;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvitationResponse {
    public String emailTo;
    public String emailFrom;
    public Long projectId;
    public Status status;
    public UUID invUUID;

}
