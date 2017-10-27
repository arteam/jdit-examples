package jdit.testing.dao;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/22/15
 * Time: 4:17 PM
 *
 * @author Artem Prigoda
 */
public class CityMapper implements RowMapper<City> {

    @Override
    public City map(ResultSet r, StatementContext statementContext) throws SQLException {
        Object[] locationArray = (Object[]) r.getArray("location").getArray();
        double[] location = new double[locationArray.length];
        for (int i = 0; i < locationArray.length; i++) {
            location[i] = (double) locationArray[i];
        }
        return new City(r.getLong("id"), r.getString("name"), r.getString("region_code"),
                r.getString("country_code"), location);
    }

}

