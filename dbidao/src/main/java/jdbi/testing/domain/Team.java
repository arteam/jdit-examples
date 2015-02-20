package jdbi.testing.domain;

/**
 * Date: 2/21/15
 * Time: 12:32 AM
 *
 * @author Artem Prigoda
 */
public class Team {

    private final long id;
    private final String name;
    private final Division division;

    public Team(long id, String name, Division division) {
        this.id = id;
        this.name = name;
        this.division = division;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Division getDivision() {
        return division;
    }
}
