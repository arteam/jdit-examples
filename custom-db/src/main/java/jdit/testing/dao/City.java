package jdit.testing.dao;

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
    public final double[] location;

    public City(long id, String name, String regionCode, String countryCode, double[] location) {
        this.id = id;
        this.name = name;
        this.regionCode = regionCode;
        this.countryCode = countryCode;
        this.location = location;
    }
}

