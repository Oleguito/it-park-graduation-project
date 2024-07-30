package ru.itpark.projectservice.presentation.projects.dto.query;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.projectservice.domain.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.valueobjects.Status;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectQuery {
    private Long id;
    
    private String name;
    
    private String description;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    private Status status;
    
    private Long ownerId;
    
    private DateInfo dateInfo;
}
