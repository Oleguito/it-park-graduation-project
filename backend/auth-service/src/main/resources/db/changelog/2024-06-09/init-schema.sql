
CREATE SEQUENCE users_id_seq OWNED BY authservice;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
UPDATE users SET id = nextval('users_id_seq');

