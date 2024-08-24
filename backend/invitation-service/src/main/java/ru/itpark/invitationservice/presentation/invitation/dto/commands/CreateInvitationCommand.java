package ru.itpark.invitationservice.presentation.invitation.dto.commands;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateInvitationCommand {
    @NotBlank(message = "поле \"emailTo\" должно быть заполнено")
    public String emailTo;
    @NotBlank(message = "поле \"emailFrom\" должно быть заполнено")
    public String emailFrom;
    @NotNull
    public Long projectId;
}
