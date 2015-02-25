create table countries (
  alpha2_code varchar(2) primary key,
  name varchar(128) not null,
  unique (name)
);
