
CREATE SEQUENCE IF NOT EXISTS proj_seq_gen;
CREATE TABLE IF NOT EXISTS projects
(
    id bigint primary key default nextval('proj_seq_gen'),
    name varchar(500) not null,
    start_date timestamp,
    end_date timestamp,
    status varchar(60) check ( status in ('Новый', 'Запланирован', 'В работе', 'На подтверждении', 'Подтвержден', 'Отменен')),
    owner_id bigint,
    created_at timestamp,
    deleted_at timestamp

)