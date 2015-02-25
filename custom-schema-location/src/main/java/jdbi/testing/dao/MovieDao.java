package jdbi.testing.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Date: 2/22/15
 * Time: 5:17 PM
 *
 * @author Artem Prigoda
 */
@RegisterMapper({MovieMapper.class, Actor.ActorMapper.class})
public interface MovieDao {

    @SqlQuery("select id, name, year, director from movies " +
            "where year=:year")
    public List<Movie> getMovies(@Bind("year") int year);

    @SqlQuery("select id, first_name, last_name, gender, c.alpha2_code, c.name from actors a " +
            "inner join countries c on a.nationality=c.alpha2_code " +
            "where a.nationality = :country.code " +
            "order by gender='female' desc, last_name")
    public List<Actor> getActors(@BindCountry Country country);
}
