create table countries (
  alpha2_code primary key,
  name varchar(128) not null,
  unique (name)
);
