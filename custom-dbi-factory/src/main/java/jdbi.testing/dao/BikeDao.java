package jdbi.testing.dao;

import com.google.common.base.Functions;
import com.google.common.collect.FluentIterable;
import jdbi.testing.domain.Bike;
import jdbi.testing.domain.Model;
import jdbi.testing.domain.Type;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * date: 3/14/15
 * Time: 9:22 PM
 *
 * @author Artem Prigoda
 */
@RegisterMapper(BikeDao.BikeMapper.class)
public abstract class BikeDao {

    @SqlQuery("select b.id, b.size," +
            " m.id model_id, mf.name manufacturer_name, m.name model_name, m.year, m.type_name," +
            " array (select bc.color from bike_colors bc where bc.bike_id=b.id) colors" +
            " from bikes b" +
            " inner join models m on b.model_id=m.id" +
            " inner join manufacturers mf on mf.id=m.manufacturer_id")
    public abstract List<Bike> getBikes();

    public static class BikeMapper implements ResultSetMapper<Bike> {

        @Override
        public Bike map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            long id = r.getLong("id");
            int size = r.getInt("size");
            long modelId = r.getLong("model_id");
            String manufacturerName = r.getString("manufacturer_name");
            String modelName = r.getString("model_name");
            int year = r.getInt("year");
            String type = r.getString("type_name");
            List<String> colors = FluentIterable.of((Object[]) r.getArray("colors").getArray())
                    .transform(Functions.toStringFunction())
                    .toList();
            Model model = new Model(modelId, manufacturerName, modelName, year, Type.valueOf(type.toUpperCase()));
            return new Bike(id, model, size, colors);
        }
    }
}
