package jdbi.testing.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("model", model)
                .add("size", size)
                .add("colours", colours)
                .toString();
    }
}

