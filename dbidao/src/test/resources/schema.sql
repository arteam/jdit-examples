create table players (
  id         bigint identity,
  first_name varchar(32) not null,
  last_name  varchar(32) not null,
  number     int         not null,
  position   varchar(5)  not null,
  check (position in ('G', 'D', 'LW', 'C', 'RW'))
);

create table divisions (
  name varchar(16) primary key
);

create table teams (
  id       bigint identity,
  name     varchar(32) not null,
  division varchar(32) not null,
  foreign key (division) references divisions (name)
);

create table roster (
  team_id   bigint not null,
  player_id bigint not null,
  primary key (team_id, player_id),
  foreign key (team_id) references teams (id),
  foreign key (player_id) references players (id)
)
