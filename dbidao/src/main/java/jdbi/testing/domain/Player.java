package jdbi.testing.domain;

/**
 * Date: 2/21/15
 * Time: 12:35 AM
 *
 * @author Artem Prigoda
 */
public class Player {

    public final long id;
    public final String firstName;
    public final String lastName;
    public final int number;
    public final Position position;

    public Player(long id, String firstName, String lastName, int number, Position position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.position = position;
    }

    public Player(String firstName, String lastName, int number, Position position) {
        this(0, firstName, lastName, number, position);
    }
}
