package jdit.testing.dao;


import org.jdbi.v3.sqlobject.config.RegisterArgumentFactory;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Date: 2/22/15
 * Time: 3:06 PM
 *
 * @author Artem Prigoda
 */
@RegisterRowMapper(CityMapper.class)
@RegisterArgumentFactory(DoubleArrayArgumentFactory.class)
public interface CityDao {

    @SqlUpdate("insert into cities(country_code, region_code, name, location) " +
            "values (:country_code, :region_code, :name, :location)")
    @GetGeneratedKeys
    long addCity(@Bind("country_code") String countryCode, @Bind("region_code") String regionCode,
                 @Bind("name") String name, @Bind("location") double[] location);

    @SqlQuery("select id, name, country_code, region_code, location from cities where id=:city_id")
    City getCity(@Bind("city_id") long cityId);
}

