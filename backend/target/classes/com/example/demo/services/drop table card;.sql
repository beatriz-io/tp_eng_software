-- drop table card;
-- create table card(
--     id bigserial primary key,
--     title varchar(100) not null,
--     description text,
--     start_date timestamp,
--     due_date timestamp,
--     priority INTEGER,
--     created_at timestamp not null default now(),
--     updated_at timestamp,
--     approved BOOLEAN,
--     hash varchar(100) not null unique,
--     owner_id bigint not null references user_account(id),
--     developer_id bigint references user_account(id)
-- )
select *
from card