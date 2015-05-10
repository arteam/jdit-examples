package jdit.testing.dao;

import jdit.testing.domain.*;
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
@RegisterMapper({Movie.MovieMapper.class, Actor.ActorMapper.class})
public interface MovieDao {

    @SqlQuery("select id, name, year, director from movies where year=:year")
    List<Movie> getMovies(@Bind("year") int year);

    @SqlQuery("movieDao/get-actors.sql")
    List<Actor> getActors(@BindCountry Country country);
}
