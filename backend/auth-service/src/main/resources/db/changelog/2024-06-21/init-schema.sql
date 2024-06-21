alter table users
ADD column if not exists role varchar(255) check ( role in ('USER', 'ADMIN'));

