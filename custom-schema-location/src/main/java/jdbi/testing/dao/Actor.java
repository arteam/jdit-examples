package jdbi.testing.dao;

import com.google.common.base.MoreObjects;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 2/25/15
 * Time: 11:48 PM
 *
 * @author Artem Prigoda
 */
public class Actor {

    public final long id;
    public final String firstName;
    public final String lastName;
    public final Gender gender;
    public final Country nationality;

    public Actor(long id, String firstName, String lastName, Gender gender, Country nationality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("gender", gender)
                .add("nationality", nationality)
                .toString();
    }

    public static class ActorMapper implements ResultSetMapper<Actor> {

        private final Country.CountryMapper countryMapper = new Country.CountryMapper();

        @Override
        public Actor map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            return new Actor(r.getLong("id"), r.getString("first_name"), r.getString("last_name"),
                    Gender.valueOf(r.getString("gender").toUpperCase()), countryMapper.map(index, r, ctx));
        }
    }
}
