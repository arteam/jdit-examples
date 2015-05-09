package jdit.testing;

import java.util.Date;

/**
 * Date: 2/7/15
 * Time: 5:00 PM
 *
 * @author Artem Prigoda
 */
public class Player {

    public final long id;
    public final String firstName;
    public final String lastName;
    public final Date birthDate;
    public final int height;
    public final int weight;

    public Player(long id, String firstName, String lastName, Date birthDate, int height, int weight) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
    }
}
