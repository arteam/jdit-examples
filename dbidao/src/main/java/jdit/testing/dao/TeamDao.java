package jdit.testing.dao;

import jdit.testing.domain.Division;
import jdit.testing.domain.Player;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

/**
 * Date: 2/21/15
 * Time: 12:38 AM
 *
 * @author Artem Prigoda
 */
public class TeamDao {

    private final Jdbi dbi;

    public TeamDao(Jdbi dbi) {
        this.dbi = dbi;
    }

    public long addTeam(final String name, final Division division, final List<Player> players) {
        return dbi.inTransaction(h -> addTeam0(h, name, division, players));
    }

    private Long addTeam0(Handle h, String name, Division division, List<Player> players) {
        Long teamId = h.createUpdate("insert into teams(name, division) values (:name, :division)")
                .bind("name", name)
                .bind("division", division.name())
                .executeAndReturnGeneratedKeys()
                .mapTo(Long.class)
                .findOnly();
        for (Player player : players) {
            Long playerId = h.createUpdate(
                    "insert into players(first_name, last_name, number, position) values (?,?,?,?)")
                    .bind(0, player.firstName)
                    .bind(1, player.lastName)
                    .bind(2, player.number)
                    .bind(3, player.position.code)
                    .executeAndReturnGeneratedKeys()
                    .mapTo(Long.class)
                    .findOnly();
            ;
            h.execute("insert into roster(team_id, player_id) values (?,?)", teamId, playerId);
        }
        return teamId;
    }
}
