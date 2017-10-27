package jdit.testing.domain;

import com.google.common.base.MoreObjects;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/23/15
 * Time: 10:24 PM
 *
 * @author Artem Prigoda
 */
public class Movie {

    public final long id;
    public final String name;
    public final int year;
    public final String director;

    public Movie(long id, String name, int year, String director) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("year", year)
                .add("director", director)
                .toString();
    }

    public static class MovieMapper implements RowMapper<Movie> {

        @Override
        public Movie map(ResultSet r, StatementContext statementContext) throws SQLException {
            return new Movie(r.getLong("id"), r.getString("name"), r.getInt("year"), r.getString("director"));
        }
    }
}
