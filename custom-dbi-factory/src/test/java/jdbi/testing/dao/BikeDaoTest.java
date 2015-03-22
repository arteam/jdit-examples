package jdbi.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedDao;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import com.google.common.collect.ImmutableMap;
import jdbi.testing.domain.Bike;
import jdbi.testing.domain.Type;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

/**
 * Date: 3/14/15
 * Time: 9:50 PM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
public class BikeDaoTest {

    @TestedDao
    BikeDao bikeDao;

    @Test
    @DataSet({"bikeDao/types.sql", "bikeDao/manufacturers.sql", "bikeDao/bikes.sql"})
    public void testGetBikes() throws Exception {
        List<Bike> bikes = bikeDao.getBikes();
        for (Bike bike : bikes) {
            System.out.println(bike);
        }
        Assert.assertEquals(bikes.size(), 14);
    }

    @Test
    @DataSet({"bikeDao/types.sql", "bikeDao/manufacturers.sql", "bikeDao/bikes.sql"})
    public void testTopColors() {
        Map<Type, String> topColors = bikeDao.getTopColors();
        for (Map.Entry<Type, String> entry : topColors.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        Assert.assertEquals(topColors, ImmutableMap.of(Type.CROSS, "red", Type.CROSS_COUNTRY, "yellow",
                Type.ROAD, "blue"));
    }
}

