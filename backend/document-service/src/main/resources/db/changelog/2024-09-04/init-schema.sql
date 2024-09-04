CREATE SEQUENCE IF NOT EXISTS documents_seq_gen;

CREATE TABLE IF NOT EXISTS document
(
    id         bigint PRIMARY KEY,
    file_name  VARCHAR(500),
    link       VARCHAR(1000),
    project_id bigint,
    user_id bigint,
    created_at TIMESTAMP
);

