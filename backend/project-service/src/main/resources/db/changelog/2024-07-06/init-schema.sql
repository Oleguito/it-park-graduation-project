
CREATE SEQUENCE IF NOT EXISTS proj_seq_gen;
CREATE TABLE IF NOT EXISTS projects
(
    id bigint primary key default nextval('proj_seq_gen'),
    name varchar(500) not null,
    description varchar(5000) not null,
    start_date timestamp,
    end_date timestamp,
    status varchar(60) check ( status in ('NEW', 'PLANNED', 'STARTED', 'CONFIRMATION', 'CONFIRMED', 'CANCELLED')),
    user_id bigint,
    created_at timestamp,
    deleted_at timestamp

)