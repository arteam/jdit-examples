package jdit.testing.domain;

import com.google.common.base.MoreObjects;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizerFactory;
import org.jdbi.v3.sqlobject.customizer.SqlStatementParameterCustomizer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/25/15
 * Time: 11:50 PM
 *
 * @author Artem Prigoda
 */
public class Country {

    public final static Country GB = new Country("GB", "Great Britain");

    public final String code;
    public final String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("name", name)
                .toString();
    }

    public static class CountryMapper implements RowMapper<Country> {
        @Override
        public Country map(ResultSet r, StatementContext statementContext) throws SQLException {
            return new Country(r.getString("alpha2_code"), r.getString("name"));
        }
    }

    public static class CountryBinder implements SqlStatementCustomizerFactory {

        @Override
        public SqlStatementParameterCustomizer createForParameter(Annotation annotation, Class<?> sqlObjectType,
                                                                  Method method, Parameter param, int index,
                                                                  Type paramType) {
            return (q, arg) -> {
                Country c = (Country) arg;
                q.bind("country" + "." + "code", c.code);
                q.bind("country" + "." + "name", c.name);
            };
        }
    }
}
