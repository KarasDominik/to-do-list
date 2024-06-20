alter table task
    add column created_date timestamptz not null default now();