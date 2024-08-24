CREATE SEQUENCE IF NOT EXISTS invitations_seq_gen;

CREATE TABLE invitations
(
    id         bigint PRIMARY KEY,
    email_to   VARCHAR(255),
    email_from VARCHAR(255),
    project_id VARCHAR(255),
    status     varchar(50),
    created_at TIMESTAMP
);

