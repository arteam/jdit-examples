package jdbi.testing.dao;

import com.google.common.collect.Maps;
import jdbi.testing.domain.Bike;
import jdbi.testing.domain.Model;
import jdbi.testing.domain.Type;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Folder2;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.util.StringMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * date: 3/14/15
 * Time: 9:22 PM
 *
 * @author Artem Prigoda
 */
public class BikeDao {

    private DBI dbi;

    public BikeDao(DBI dbi) {
        this.dbi = dbi;
    }

    public List<Bike> getBikes() {
        try (Handle handle = dbi.open()) {
            return handle.createQuery("select b.id, b.size," +
                    " m.id model_id, mf.name manufacturer_name, m.name model_name, m.year, m.type_name" +
                    " from bikes b" +
                    " inner join models m on b.model_id=m.id" +
                    " inner join manufacturers mf on mf.id=m.manufacturer_id")
                    .map(new ResultSetMapper<Bike>() {
                        @Override
                        public Bike map(int index, ResultSet r, StatementContext ctx) throws SQLException {
                            return createBike(r);
                        }
                    }).list();
        }
    }

    public Map<Type, String> getTopColors() {
        try (Handle handle = dbi.open()) {
            return handle.createQuery("with top_colors as " +
                    "(select  m.type_name, bc.color, count(*) cnt " +
                    " from models m " +
                    " inner join bikes b on b.model_id = m.id " +
                    " inner join bike_colors bc on bc.bike_id = b.id " +
                    " group by m.type_name, bc.color " +
                    " order by m.type_name) " +
                    "select tc1.* from top_colors tc1 " +
                    "where not exists(select * from top_colors tc2 " +
                    " where tc1.type_name=tc2.type_name " +
                    " and (tc1.cnt<tc2.cnt or (tc1.cnt = tc2.cnt and tc1.color > tc2.color)) " +
                    ")").fold(new LinkedHashMap<Type, String>(), new Folder2<Map<Type, String>>() {
                @Override
                public Map<Type, String> fold(Map<Type, String> accumulator, ResultSet rs, StatementContext ctx) throws SQLException {
                    accumulator.put(Type.valueOf(rs.getString("type_name").toUpperCase()), rs.getString("color"));
                    return accumulator;
                }
            });
        }
    }

    public List<String> getBikeColors(long bikeId) {
        try (Handle h = dbi.open()) {
            return h.createQuery("select color from bike_colors where bike_id=:bike_id")
                    .bind("bike_id", bikeId)
                    .map(StringMapper.FIRST)
                    .list();
        }
    }

    private Bike createBike(ResultSet r) throws SQLException {
        long id = r.getLong("id");
        int size = r.getInt("size");
        long modelId = r.getLong("model_id");
        String manufacturerName = r.getString("manufacturer_name");
        String modelName = r.getString("model_name");
        int year = r.getInt("year");
        String type = r.getString("type_name");

        Model model = new Model(modelId, manufacturerName, modelName, year, Type.valueOf(type.toUpperCase()));
        return new Bike(id, model, size, getBikeColors(id));
    }
}

