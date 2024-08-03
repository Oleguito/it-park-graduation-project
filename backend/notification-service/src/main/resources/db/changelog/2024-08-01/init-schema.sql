CREATE SEQUENCE IF NOT EXISTS notifications_id_seq;

CREATE TABLE notifications (
  id bigint PRIMARY KEY default nextval('notifications_id_seq'),
  user_id bigint NOT NULL,
  title VARCHAR(255) NOT NULL,
  message TEXT NOT NULL,
  type VARCHAR(50) NOT NULL,
  read BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);



--CREATE SEQUENCE IF NOT EXISTS proj_seq_gen;
--CREATE TABLE IF NOT EXISTS projects
--(
--    id bigint primary key default nextval('proj_seq_gen'),
--    name varchar(500) not null,
--    description varchar(5000) not null,
--    start_date timestamp,
--    end_date timestamp,
--    status varchar(60) check ( status in ('NEW', 'PLANNED', 'STARTED', 'CONFIRMATION', 'CONFIRMED', 'CANCELLED')),
--    user_id bigint,
--    created_at timestamp,
--    deleted_at timestamp
--
--)