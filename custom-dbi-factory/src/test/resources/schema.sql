create table manufacturers(id bigint primary key , name varchar(64));

create table models(
  id bigint primary key,
  name varchar(64) not null,
  manufacturer_id bigint not null,
  year int null,
  foreign key (manufacturer_id) references manufacturers(id),
  check (year>1900)
);

create table types(name varchar(16) primary key);

create table bikes(
  id bigint primary key,
  model_id bigint not null,
  type_id bigint not null,
  size int not null,
  colours varchar array not null
);

