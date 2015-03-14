package jdbi.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
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

    @TestedSqlObject
    BikeDao bikeDao;

    @Test
    @DataSet({"bikeDao/types.sql", "bikeDao/manufacturers.sql"})
    public void testGetBikes() throws Exception {
        List<Bike> bikes = bikeDao.getBikes();
        System.out.println(bikes);
    }
}
