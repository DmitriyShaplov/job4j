create table halls (
  id serial primary key,
  row int not null,
  place int not null,
  sold boolean default false
);

create table accounts (
  id serial primary key,
  username varchar(200) not null,
  phone varchar(100) not null,
  halls_id integer references halls(id)
);

insert into halls(row, place) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5),
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5);