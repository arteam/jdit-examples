package jdbi.testing.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Date: 2/22/15
 * Time: 3:06 PM
 *
 * @author Artem Prigoda
 */
@RegisterMapper(CityMapper.class)
public interface CityDao {

    @SqlUpdate("insert into cities(country_code, region_code, name) " +
            "values (:country_code, :region_code, :name)")
    @GetGeneratedKeys
    long addCity(@Bind("country_code") String countryCode, @Bind("region_code") String regionCode,
                 @Bind("name") String name);

    @SqlQuery("select id, name, country_code, region_code from cities where id=:city_id")
    City getCity(@Bind("city_id") long cityId);
}

