package jdbi.testing.domain;

import java.util.List;

/**
 * Date: 3/13/15
 * Time: 8:26 AM
 *
 * @author Artem Prigoda
 */
public class Bike {

    public long id;
    public Model model;
    public int size;
    public List<String> colours;

    public Bike(long id, Model model, int size, List<String> colours) {
        this.id = id;
        this.model = model;
        this.size = size;
        this.colours = colours;
    }
}
