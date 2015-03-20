create table ranks(
  name varchar(32) not null
);

create table chess_players(
  id identity primary key,
  first_name varchar(64) not null,
  last_name varchar(64) not null,
  rank_name varchar(32) not null,
  foreign key (rank_name) references ranks(name)
);

create table debuts(
  code varchar(8) not null primary key,
  name varchar(64) not null
);

create table results(
  id int primary key,
  name varchar (8)
);

create table chess_games(
  id identity primary key,
  white_player_id int not null,
  black_player_id int not null,
  result_id int not null,
  foreign key (white_player_id) references chess_players(id),
  foreign key (black_player_id) references chess_players(id),
  foreign key (result_id) references results(id),
  check (white_player_id != black_player_id)
)