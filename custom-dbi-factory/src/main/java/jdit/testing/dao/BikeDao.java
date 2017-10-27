package jdit.testing.dao;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import jdit.testing.domain.Bike;
import jdit.testing.domain.Model;
import jdit.testing.domain.Type;
import org.apache.commons.lang3.tuple.Pair;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * date: 3/14/15
 * Time: 9:22 PM
 *
 * @author Artem Prigoda
 */
public class BikeDao {

    private Jdbi dbi;

    public BikeDao(Jdbi dbi) {
        this.dbi = dbi;
    }

    public List<Bike> getBikes() {
        try (Handle handle = dbi.open()) {
            return handle.createQuery(loadScript("bikeDao/get-bikes.sql"))
                    .map((rs, ctx) -> createBike(rs)).list();
        }
    }

    public Map<Type, String> getTopColors() {
        try (Handle handle = dbi.open()) {
            return handle.createQuery(loadScript("bikeDao/get-top-colors.sql"))
                    .map((rs, ctx) -> Pair.of(Type.valueOf(rs.getString("type_name").toUpperCase()),
                            rs.getString("color")))
                    .list()
                    .stream()
                    .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        }
    }

    public List<String> getBikeColors(long bikeId) {
        try (Handle h = dbi.open()) {
            return h.createQuery("select color from bike_colors where bike_id=:bike_id")
                    .bind("bike_id", bikeId)
                    .mapTo(String.class)
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

    private static String loadScript(String resourceName) {
        try {
            return Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

