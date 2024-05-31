CREATE TABLE Users
(
    id         bigint PRIMARY KEY,
    full_name  VARCHAR(200),
    email      VARCHAR(255) UNIQUE,
    login      VARCHAR(255) UNIQUE,
    languages  jsonb,
    created_at TIMESTAMP,
    deleted_at TIMESTAMP
);


--alter table managerscompanies
--    add column id serial primary key;

--create table DriversLicenses
--(
--    id   bigint primary key,
--    name varchar(100)
--);
--
--create table Drivers
--(
--    id               bigint primary key,
--    driver_licenses  bigint references DriversLicenses (id),
--    sum_way          float,
--    experience_month int,
--    rating           float,
--    owner_id         bigint references Users (id)
--);
--
--create table Managers
--(
--    id       bigint primary key,
--    owner_id bigint references Users (id),
--    rating   float,
--    tariff   jsonb
--);
--
--create table ManagersCompanies
--(
--    manager_id bigint references Managers (id),
--    company_id bigint
--);
