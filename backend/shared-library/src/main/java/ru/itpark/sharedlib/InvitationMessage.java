package ru.itpark.sharedlib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitationMessage {
    
    private String projectCreatorEmail;
    private String invitedUserEmail;
    private String invitationMessage;
    
    private Long projectId;
    private String projectTitle;
    private String projectStatus;
    private String projectDescription;

    generateMessage();
}
