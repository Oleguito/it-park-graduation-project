package ru.itpark.projectservice.domain.project.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateInfo {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
