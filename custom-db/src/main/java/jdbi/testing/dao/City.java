package jdbi.testing.dao;

/**
 * Date: 2/22/15
 * Time: 4:16 PM
 *
 * @author Artem Prigoda
 */
public class City {

    public final long id;
    public final String name;
    public final String regionCode;
    public final String countryCode;

    public City(long id, String name, String regionCode, String countryCode) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.countryCode = countryCode;
    }
}

