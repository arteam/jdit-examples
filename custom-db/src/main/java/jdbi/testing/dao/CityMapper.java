package jdbi.testing.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/22/15
 * Time: 4:17 PM
 *
 * @author Artem Prigoda
 */
public class CityMapper implements ResultSetMapper<City> {
    @Override
    public City map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new City(r.getLong("id"), r.getString("name"), r.getString("region_code"),
                r.getString("country_code"), (double[]) r.getArray("location").getArray());
    }
}

