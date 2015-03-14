package jdbi.testing.domain;

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
    public Type type;

    public Model(long id, String manufacturer, String name, int year, Type type) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.name = name;
        this.year = year;
        this.type = type;
    }
}
