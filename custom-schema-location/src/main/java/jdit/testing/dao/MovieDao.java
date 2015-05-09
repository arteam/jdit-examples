package jdit.testing.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
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

    @SqlQuery("select id, name, year, director from movies where year=:year")
    public List<Movie> getMovies(@Bind("year") int year);

    @SqlQuery("movieDao/get-actors.sql")
    public List<Actor> getActors(@BindCountry Country country);
}
