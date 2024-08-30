package ru.itpark.invitationservice.presentation.invitation.dto.commands;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.itpark.invitationservice.domain.invitations.VO.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateInvitationCommand {
    @NotBlank(message = "поле \"emailTo\" должно быть заполнено")
    public String emailTo;
    @NotBlank(message = "поле \"emailFrom\" должно быть заполнено")
    public String emailFrom;
    @NotNull
    public Long projectId;

    public Status status;
    
    /**
     * на этапе создания комментария может быть
     * "invite" или "exclude"
     * **/
    public String type;
    
    public String invitationMessage;
}
