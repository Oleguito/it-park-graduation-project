package ru.itpark.invitationservice.presentation.invitation.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteInvitationCommand {
    private String invUUID;
    private String emailFrom;
}
