ALTER TABLE projects
DROP COLUMN user_id;
ALTER TABLE projects
ADD COLUMN IF NOT EXISTS owner_email varchar(255) NOT NULL
CONSTRAINT email_format CHECK (owner_email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')