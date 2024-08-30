package ru.itpark.projectservice.infrastructure.invitationservice.invitation.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.projectservice.infrastructure.invitationservice.invitationstatus.InvitationStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInvitationCommand {

    public String emailTo;
    public String emailFrom;
    public Long projectId;

    public InvitationStatus invitationStatus;
    
    /**
     * на этапе создания комментария может быть
     * "invite" или "exclude"
     * **/
    public String type;
    
    public String invitationMessage;
}
