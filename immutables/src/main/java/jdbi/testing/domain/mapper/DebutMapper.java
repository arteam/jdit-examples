package jdbi.testing.domain.mapper;

import jdbi.testing.ImmutableDebut;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:55
 *
 * @author Artem Prigoda
 */
public class DebutMapper implements ResultSetMapper<ImmutableDebut> {
    @Override
    public ImmutableDebut map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return ImmutableDebut.builder()
                .code(r.getString("debut_code"))
                .name(r.getString("debut_name"))
                .build();
    }
}
