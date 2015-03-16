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

    public Map<Type, String> getTopColors(){
        try (Handle handle = dbi.open()){
           return handle.createQuery(
                   "select t.name, top_color.* from types t " +
                   "lateral (select bc.color, cnt from models m " +
                           "inner join bikes b on b.model_id=m.id " +
                           "inner join bike_colors bc on bc.bike_id=b.id " +
                           "where m.type_name=t.name " +
                           "group by m.type_name, bc.color " +
                           "order by cnt desc " +
                           "limit 1) as top_color")
                   .fold(new HashMap<Type, String>(), new Folder2<HashMap<Type, String>>() {
                       @Override
                       public HashMap<Type, String> fold(HashMap<Type, String> accumulator, ResultSet rs, StatementContext ctx) throws SQLException {
                           System.out.println(Type.valueOf(rs.getString("type_name").toUpperCase()) + " " + rs.getString("color") + " " + rs.getInt("cnt"));
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
