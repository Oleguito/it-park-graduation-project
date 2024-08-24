package ru.itpark.invitationservice.domain.invitations;

import jakarta.persistence.*;
import ru.itpark.invitationservice.domain.invitations.VO.enums.Status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invitations_seq_gen")
    @SequenceGenerator(name = "invitations_seq_gen", sequenceName = "invitations_seq_gen", allocationSize = 1)
    public Long id;

    @Column(name = "email_to")
    public String emailTo;

    @Column(name = "email_from")
    public String emailFrom;

    @Column(name = "project_id")
    public Long projectId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    public Status status;

    @Column(name = "created_at")
    public LocalDateTime created_at = LocalDateTime.now();

}
