alter table users add column password text;
alter table users add column role text;
update users set password = '123456';
update users set role = 'USER';
alter table users alter column password set not null;
alter table users alter column role set not null;
insert into users (login, password, name, email, created, role)
values ('admin', 'admin', 'admin', 'shaplovd@gmail.com', now(), 'ADMIN');