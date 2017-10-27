package jdit.testing;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/19/15
 * Time: 11:43 PM
 *
 * @author Artem Prigoda
 */
public class PlayerMapper implements RowMapper<Player> {

    @Override
    public Player map(ResultSet r, StatementContext ctx) throws SQLException {
        return new Player(r.getLong("id"), r.getString("first_name"), r.getString("last_name"),
                r.getTimestamp("birth_date"), r.getInt("height"), r.getInt("weight"));

    }
}
