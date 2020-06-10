create table if not exists users (
    id identity not null primary key,
    first_name varchar(255),
    last_name varchar(255),
    registration_date datetime
);
