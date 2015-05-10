package jdit.testing.domain;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/23/15
 * Time: 10:25 PM
 *
 * @author Artem Prigoda
 */
public class MovieMapper implements ResultSetMapper<Movie> {

    @Override
    public Movie map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Movie(r.getLong("id"), r.getString("name"), r.getInt("year"), r.getString("director"));
    }
}
