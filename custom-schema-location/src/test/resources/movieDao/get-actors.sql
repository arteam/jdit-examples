select id, first_name, last_name, gender, c.alpha2_code, c.name from actors a
inner join countries c on a.nationality=c.alpha2_code
where a.nationality = :country.code
order by gender='female' desc, last_name