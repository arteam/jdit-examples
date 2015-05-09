package jdit.testing.dao;

import com.google.common.base.MoreObjects;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.lang.annotation.Annotation;
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

    public static class CountryMapper implements ResultSetMapper<Country> {
        @Override
        public Country map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Country(r.getString("alpha2_code"), r.getString("name"));
        }
    }

    public static class CountryBinder implements BinderFactory {
        @Override
        public Binder build(Annotation annotation) {
            return new Binder<BindCountry, Country>() {
                @Override
                public void bind(SQLStatement<?> q, BindCountry bind, Country arg) {
                    q.bind(bind.value() + "." + "code", arg.code);
                    q.bind(bind.value() + "." + "name", arg.name);
                }
            };
        }
    }
}
