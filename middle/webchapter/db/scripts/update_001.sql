create table users (
  id serial primary key,
  login varchar(50) unique not null,
  name varchar(50),
  email varchar(50),
  created timestamp
);