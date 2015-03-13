create table manufacturers (id bigint primary key, name varchar(64));
create table types (name varchar(16) primary key);

create table models (
  id              bigint primary key,
  name            varchar(64) not null,
  manufacturer_id bigint      not null,
  type_name       bigint      not null,
  year            int         null,
  foreign key (manufacturer_id) references manufacturers (id),
  foreign key (type_name) references types (name),
  check (year > 0),
  check (length(name) > 0)
);

create table bikes (
  id       bigint primary key,
  model_id bigint not null,
  size     int    not null,
  foreign key (model_id) references models (id),
  check (size > 0)
);

create table bike_colors (
  bike_id bigint      not null,
  color   varchar(16) not null,
  primary key (bike_id, color),
  foreign key (bike_id) references bikes (id),
  check (length(color) > 0)
);

