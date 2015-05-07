select b.id, b.size,
 m.id model_id, mf.name manufacturer_name, m.name model_name, m.year, m.type_name
 from bikes b
 inner join models m on b.model_id=m.id
 inner join manufacturers mf on mf.id=m.manufacturer_id