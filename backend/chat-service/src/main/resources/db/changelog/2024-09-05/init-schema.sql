CREATE SEQUENCE IF NOT EXISTS chat_seq_gen START 1;

CREATE TABLE IF NOT EXISTS chats (
                                     id BIGSERIAL PRIMARY KEY,
                                     project_id BIGINT,
                                     project_name TEXT
);
CREATE SEQUENCE IF NOT EXISTS message_seq_gen START 1;

CREATE TABLE IF NOT EXISTS messages (
                                        id BIGSERIAL PRIMARY KEY,
                                        chat_id BIGINT NOT NULL,
                                        user_id BIGINT,
                                        username VARCHAR(255),
                                        sent_at TIMESTAMP DEFAULT NOW(),
                                        message TEXT,
                                        CONSTRAINT fk_chat
                                            FOREIGN KEY (chat_id)
                                                REFERENCES chats (id)
                                                ON DELETE CASCADE
);
