package jdit.testing.dao;

import jdit.testing.domain.Actor;
import jdit.testing.domain.BindCountry;
import jdit.testing.domain.Country;
import jdit.testing.domain.Movie;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

/**
 * Date: 2/22/15
 * Time: 5:17 PM
 *
 * @author Artem Prigoda
 */
@UseClasspathSqlLocator
public interface MovieDao {

    @SqlQuery
    @RegisterRowMapper(Movie.MovieMapper.class)
    List<Movie> getMovies(@Bind("year") int year);

    @SqlQuery
    @RegisterRowMapper(Actor.ActorMapper.class)
    List<Actor> getActors(@BindCountry Country country);
}
