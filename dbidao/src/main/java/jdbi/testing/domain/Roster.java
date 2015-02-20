package jdbi.testing.domain;

import java.util.List;

/**
 * Date: 2/21/15
 * Time: 12:34 AM
 *
 * @author Artem Prigoda
 */
public class Roster {

    public final Team team;
    public final List<Player> players;

    public Roster(Team team, List<Player> players) {
        this.team = team;
        this.players = players;
    }
}
