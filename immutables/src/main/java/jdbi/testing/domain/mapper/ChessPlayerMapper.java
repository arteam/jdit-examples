package jdbi.testing.domain.mapper;

import jdbi.testing.ImmutableChessPlayer;
import jdbi.testing.domain.Rank;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:58
 *
 * @author Artem Prigoda
 */
public class ChessPlayerMapper implements ResultSetMapper<ImmutableChessPlayer> {

    @Override
    public ImmutableChessPlayer map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return ImmutableChessPlayer.builder()
                .firstName(r.getString("player_first_name"))
                .lastName(r.getString("player_last_name"))
                .rank(Rank.valueOf(r.getString("player_rank").toUpperCase()))
                .build();
    }

    public ImmutableChessPlayer map(String prefix, ResultSet r) throws SQLException {
        return ImmutableChessPlayer.builder()
                .firstName(r.getString(prefix + "player_first_name"))
                .lastName(r.getString(prefix + "player_last_name"))
                .rank(Rank.valueOf(r.getString(prefix + "player_rank").toUpperCase()))
                .build();
    }
}
