package jdbi.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedDao;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import jdbi.testing.domain.Bike;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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
    }
}
