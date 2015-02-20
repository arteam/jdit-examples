package jdbi.testing.dao;

import jdbi.testing.domain.Division;
import jdbi.testing.domain.Player;
import org.skife.jdbi.v2.*;
import org.skife.jdbi.v2.util.LongMapper;

import java.util.List;

/**
 * Date: 2/21/15
 * Time: 12:38 AM
 *
 * @author Artem Prigoda
 */
public class TeamDao {

    private final DBI dbi;

    public TeamDao(DBI dbi) {
        this.dbi = dbi;
    }

    public long addTeam(final String name, final Division division, final List<Player> players) {
        return dbi.inTransaction(new TransactionCallback<Long>() {
            @Override
            public Long inTransaction(Handle h, TransactionStatus status) throws Exception {
                Long teamId = h.createStatement("insert into teams(name, division) values (:name, :division)")
                        .bind("name", name)
                        .bind("division", division.name())
                        .executeAndReturnGeneratedKeys(LongMapper.FIRST)
                        .first();
                for (Player player : players) {
                    Long playerId = h.createStatement(
                            "insert into players(first_name, last_name, number, position) values (?,?,?,?)")
                            .bind(0, player.firstName)
                            .bind(1, player.lastName)
                            .bind(2, player.number)
                            .bind(3, player.position.code)
                            .executeAndReturnGeneratedKeys(LongMapper.FIRST)
                            .first();
                    h.insert("insert into roster(team_id, player_id) values (?,?)", teamId, playerId);
                }
                return teamId;
            }
        });
    }
}
