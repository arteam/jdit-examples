create table movies (
  id       bigint identity ,
  name     varchar(128) not null,
  year     int,
  director varchar(64)  not null,
  unique (name, year),
  check (year > 0),
  check (length(name)> 0),
  check (length(director)> 0)
);
