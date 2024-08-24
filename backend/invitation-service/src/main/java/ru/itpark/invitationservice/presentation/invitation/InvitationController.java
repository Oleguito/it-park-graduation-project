package ru.itpark.invitationservice.presentation.invitation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itpark.invitationservice.application.invitations.service.InvitationService;
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

}
