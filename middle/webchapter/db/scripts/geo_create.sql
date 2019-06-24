create table countries(
  id serial primary key,
  title varchar(50)
);

create table cities(
  id serial primary key,
  title varchar(50),
  countries_id integer references countries(id)
);