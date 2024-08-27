package ru.itpark.invitationservice.presentation.invitation.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetInvitationStatusCommand {

    public String emailTo;
    public String invUUID;
    public String status;
    public String emailFrom;

}
