CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS participant_project (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_email varchar(255) NOT NULL,
    project_id BIGINT NOT NULL
);