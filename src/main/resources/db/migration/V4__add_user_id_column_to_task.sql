delete from task;

alter table task
    add column user_id uuid not null;

alter table task
    add constraint task_user_id_fk foreign key (user_id) references user_account(id);