package ru.itpark.projectservice.presentation.invitations;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invitations")
public class InvitationsController {
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public void getMyInvitations() {

        
    }
}
