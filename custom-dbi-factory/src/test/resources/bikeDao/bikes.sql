insert into models (id, name, manufacturer_id, type_name, year) values
(1, 'Synergy', 1, 'cross', 2015),
(2, 'Avion', 1, 'cross', 2014),
(3, 'Codex', 1, 'cross', 2014),
(4, 'Introvert', 1, 'cross_country', 2015),
(5, 'Vectra', 1, 'cross_country', 2014),
(6, 'Charisma 77', 1, 'road', 2015),
(7, 'Aura 44', 1, 'road', 2015),
(8, 'Crossway 10-V', 2, 'cross', 2015),
(9, 'Crossway 15-D', 2, 'cross', 2015),
(10, 'Big.Nine', 2, 'road', 2014);

insert into bikes(id, model_id, size) values
(1, 1, 16),
(2, 1, 18),
(3, 2, 16),
(4, 3, 20),
(5, 3, 22),
(6, 3, 24),
(7, 4, 16),
(8, 6, 16),
(9, 7, 18),
(10, 8, 20),
(11, 8, 18),
(12, 8, 22),
(13, 10, 16),
(14, 10, 24);

insert into bike_colors(bike_id, color) values
(1, 'red'),
(1, 'white'),
(2, 'red'),
(2, 'white'),
(3, 'green'),
(4, 'blue'),
(5, 'green'),
(6, 'red'),
(7, 'yellow'),
(8, 'purple'),
(8, 'white'),
(9, 'blue'),
(10, 'orange'),
(11, 'black'),
(12, 'grey'),
(13, 'silver'),
(14, 'golden');

