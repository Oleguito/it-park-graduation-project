ALTER TABLE invitations ADD COLUMN IF NOT EXISTS type varchar(255);
ALTER TABLE invitations ADD COLUMN IF NOT EXISTS message varchar(255);