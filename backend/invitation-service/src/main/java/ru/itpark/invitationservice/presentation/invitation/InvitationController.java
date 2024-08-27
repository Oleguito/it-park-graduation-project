package ru.itpark.invitationservice.presentation.invitation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itpark.invitationservice.application.invitations.service.InvitationService;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.DeleteInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.SetInvitationStatusCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.commands.CreateInvitationCommand;
import ru.itpark.invitationservice.presentation.invitation.dto.queries.InvitationSearchQuery;
import ru.itpark.invitationservice.presentation.invitation.dto.reponses.InvitationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/invitations")
@RequiredArgsConstructor
@Validated
public class InvitationController {

    private final InvitationService invitationService;

    @PostMapping
    public void createInvitation(@Valid @RequestBody CreateInvitationCommand createInvitationCommand) {
        invitationService.createInvitation(createInvitationCommand);
    }

    @PostMapping("/find")
    @ResponseBody
    public List<InvitationResponse> getInvitations(@RequestBody InvitationSearchQuery query) {
        return invitationService.getInvitations(query);
    }

    // TODO: после добавления Security добавить проверку email из токена и emailTo/emailFrom из тела запроса
    /**
     * Метод изменяет статус приглашения на ACCEPTED/DECLINED
      * @param acceptInvitation - содержит информацию о приглашении
     */
    @PatchMapping()
    public void acceptInvitation(@RequestBody SetInvitationStatusCommand acceptInvitation) {
        invitationService.setInvitationStatus(acceptInvitation);
    }

    // TODO: после добавления Security добавить проверку email из токена и emailFrom из тела запроса
    /**
     * Метод изменяет статус приглашения на ACCEPTED/DECLINED
     * @param deleteInvitationCommand - содержит индентификатор приглашения и email отправителя
     */
    @DeleteMapping
    public void deleteInvitation(@RequestBody DeleteInvitationCommand deleteInvitationCommand) {
        invitationService.deleteInvitation(deleteInvitationCommand);
    }

}
