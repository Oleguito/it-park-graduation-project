package ru.itpark.notificationservice.infrastructure.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
    private String type;

    private UUID key;
    
    private Long projectId;
    private String projectTitle;
    private String projectStatus;
    private String projectDescription;

}





