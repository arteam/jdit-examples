create table players(
    id  identity,
    first_name varchar(128) not null,
    last_name varchar(128) not null,
    birth_date date not null,
    weight int not null,
    height int not null
);
