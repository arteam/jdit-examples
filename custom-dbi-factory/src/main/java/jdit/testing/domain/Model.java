package jdit.testing.domain;

import com.google.common.base.MoreObjects;

/**
 * Date: 3/13/15
 * Time: 8:34 AM
 *
 * @author Artem Prigoda
 */
public class Model {

    public final long id;
    public final String manufacturer;
    public final String name;
    public final int year;
    public final Type type;

    public Model(long id, String manufacturer, String name, int year, Type type) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.year = year;
        this.type = type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("manufacturer", manufacturer)
                .add("name", name)
                .add("year", year)
                .add("type", type)
                .toString();
    }
}
