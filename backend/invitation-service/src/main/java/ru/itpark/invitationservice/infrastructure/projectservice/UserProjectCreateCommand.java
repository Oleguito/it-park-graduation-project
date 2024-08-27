package ru.itpark.invitationservice.infrastructure.projectservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProjectCreateCommand {

    private String email;
    private Long projectId;

}
