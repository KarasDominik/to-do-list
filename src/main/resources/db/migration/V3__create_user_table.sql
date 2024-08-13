create table user_account (
    id          uuid not null primary key,
    email       text not null unique,
    password    text not null
);