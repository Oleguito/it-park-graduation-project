
ALTER TABLE invitations ADD COLUMN IF NOT EXISTS inv_uuid uuid unique;
