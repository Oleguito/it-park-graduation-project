package ru.itpark.projectservice.domain.userproject;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "participant_project")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProject {
    
    @Id
    @GeneratedValue(generator = "uuid_gen")
    @GenericGenerator(name = "uuid_gen", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "user_email")
    private String email;
    
    @Column(name = "project_id")
    private Long project_id;
}