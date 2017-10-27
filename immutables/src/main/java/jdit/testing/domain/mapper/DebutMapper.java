package jdit.testing.domain.mapper;

import jdit.testing.domain.ImmutableDebut;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:55
 *
 * @author Artem Prigoda
 */
public class DebutMapper implements RowMapper<ImmutableDebut> {

    @Override
    public ImmutableDebut map(ResultSet r, StatementContext ctx) throws SQLException {
        return ImmutableDebut.builder()
                .code(r.getString("debut_code"))
                .name(r.getString("debut_name"))
                .build();
    }

}
