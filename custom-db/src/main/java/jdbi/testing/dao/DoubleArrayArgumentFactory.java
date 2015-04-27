package jdbi.testing.dao;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Date: 4/28/15
 * Time: 12:02 AM
 *
 * @author Artem Prigoda
 */
public class DoubleArrayArgumentFactory implements ArgumentFactory<Double[]> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext ctx) {
        return value.getClass() == Double[].class;
    }

    @Override
    public Argument build(Class<?> expectedType, final Double[] value, StatementContext ctx) {
        return new Argument() {
            @Override
            public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
                if (value != null) {
                    statement.setArray(position, ctx.getConnection().createArrayOf("NUMERIC", value));
                } else {
                    statement.setNull(position, Types.ARRAY);
                }
            }
        };
    }
}
