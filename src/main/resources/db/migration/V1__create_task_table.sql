CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table task (
    id      uuid    not null default uuid_generate_v4() primary key,
    content text    not null,
    done    boolean not null
);