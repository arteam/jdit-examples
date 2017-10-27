package jdit.testing.dao;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Date: 4/28/15
 * Time: 12:02 AM
 *
 * @author Artem Prigoda
 */
public class DoubleArrayArgumentFactory extends AbstractArgumentFactory<double[]> {

    public DoubleArrayArgumentFactory() {
        super(Types.ARRAY);
    }

    @Override
    protected Argument build(double[] value, ConfigRegistry config) {
        return new Argument() {
            @Override
            public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
                if (value != null) {
                    Object[] wrappedValue = new Object[value.length];
                    for (int i = 0; i < value.length; i++) {
                        wrappedValue[i] = value[i];
                    }
                    statement.setArray(position, ctx.getConnection().createArrayOf("NUMERIC", wrappedValue));
                } else {
                    statement.setNull(position, Types.ARRAY);
                }
            }
        };
    }
}
