package ru.itpark.sharedlib;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationMessage {
    private Long projectId;
    private String projectName;
    private String message;
}
