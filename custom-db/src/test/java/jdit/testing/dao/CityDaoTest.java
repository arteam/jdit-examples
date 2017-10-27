package jdit.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DBIHandle;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import org.jdbi.v3.core.Handle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Date: 2/22/15
 * Time: 3:10 PM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
public class CityDaoTest {

    @TestedSqlObject
    CityDao cityDao;

    @DBIHandle
    Handle handle;

    @Test
    public void testAddCity() throws Exception {
        long cityId = cityDao.addCity("US", "MO", "St. Louis", new double[]{38.6272222, -90.1977778});
        Assert.assertEquals(cityId, 1L);
    }

    @Test
    public void testGetCityNameById() {
        long cityId = 42L;
        handle.execute("insert into cities(id,country_code, region_code, name, location) values (?,?,?,?, " +
                        "array[38.6272222, -90.1977778])",
                cityId, "US", "MO", "St. Louis");

        City city = cityDao.getCity(cityId);
        Assert.assertEquals(city.id, cityId);
        Assert.assertEquals(city.name, "St. Louis");
        Assert.assertEquals(city.regionCode, "MO");
        Assert.assertEquals(city.countryCode, "US");
        Assert.assertArrayEquals(city.location, new double[]{38.6272222, -90.1977778}, 0.01);
    }
}

