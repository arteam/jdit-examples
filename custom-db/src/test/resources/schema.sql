create sequence cities_seq start with 1;
create table cities(
  id bigint default nextval('cities_seq'),
  name varchar(128) not null,
  region_code varchar(2) not null,
  country_code varchar(2) not null,
  location double array not null,
  unique (name, region_code, country_code)
);

