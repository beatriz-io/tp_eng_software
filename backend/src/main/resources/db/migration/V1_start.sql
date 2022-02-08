create table user_account(
    id bigserial primary key,
    name varchar(100),
    email varchar(100) not null,
    role varchar(100),
    created_at timestamp not null default now(),
    updated_at timestamp,
    enabled bool not null default true
);
create table card(
    id bigserial primary key,
    title varchar(100) not null,
    description text,
    start_date timestamp,
    due_date timestamp,
    priority int,
    created_at timestamp not null default now(),
    updated_at timestamp,
    hash varchar(100) not null unique,
    owner_id bigint not null references user_account(id),
    developer_id bigint references user_account(id)
)