with top_colors as
(select  m.type_name, bc.color, count(*) cnt
 from models m
 inner join bikes b on b.model_id = m.id
 inner join bike_colors bc on bc.bike_id = b.id
 group by m.type_name, bc.color
 order by m.type_name)
select tc1.* from top_colors tc1
where not exists(select * from top_colors tc2
 where tc1.type_name=tc2.type_name
 and (tc1.cnt < tc2.cnt or (tc1.cnt = tc2.cnt and tc1.color > tc2.color))
)