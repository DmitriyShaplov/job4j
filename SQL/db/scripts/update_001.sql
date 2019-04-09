create table items (
  id serial primary key not null,
  name varchar(200),
  description text,
  created timestamp
);