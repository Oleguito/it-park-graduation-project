package ru.itpark.projectservice.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.projectservice.domain.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.valueobjects.Status;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(generator = "proj_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "proj_seq_gen", name = "proj_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "description", length = 5000, nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "status", length = 60)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_id")
    private Long ownerId;

    @Embedded
    private DateInfo dateInfo;

}
