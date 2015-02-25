package jdbi.testing.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/25/15
 * Time: 11:50 PM
 *
 * @author Artem Prigoda
 */
public class Country {

    public final String code;
    public final String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static class CountryMapper implements ResultSetMapper<Country> {
        @Override
        public Country map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Country(r.getString("alpha2_code"), r.getString("name"));
        }
    }
}
