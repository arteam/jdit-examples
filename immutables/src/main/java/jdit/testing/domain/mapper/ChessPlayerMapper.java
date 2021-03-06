package jdit.testing.domain.mapper;

import jdit.testing.domain.ImmutableChessPlayer;
import jdit.testing.domain.Rank;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:58
 *
 * @author Artem Prigoda
 */
public class ChessPlayerMapper implements RowMapper<ImmutableChessPlayer> {

    @Override
    public ImmutableChessPlayer map(ResultSet r, StatementContext ctx) throws SQLException {
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
