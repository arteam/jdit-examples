create table actors (
  id          bigint identity,
  first_name  varchar(128) not null,
  last_name   varchar(128) not null,
  gender      varchar(8)   not null check (gender in ('male' 'female')),
  nationality varchar(2)   not null,
  foreign key (nationality) references countries(alpha2_code)
);
