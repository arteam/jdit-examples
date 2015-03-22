package jdbi.testing.dao;

import com.google.common.collect.ImmutableList;
import jdbi.testing.domain.ImmutableChessGame;
import jdbi.testing.domain.ChessGame;
import jdbi.testing.domain.ImmutableChessPlayer;
import jdbi.testing.domain.mapper.ChessPlayerMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Date: 18.03.15
 * Time: 20:54
 *
 * @author Artem Prigoda
 */
public interface ChessDao {

    @SqlQuery("select * from chess_games")
    public ImmutableList<ImmutableChessGame> getChessGames();

    @SqlQuery("select first_name as player_first_name, last_name as player_last_name," +
            " rank_name player_rank from chess_players")
    @Mapper(ChessPlayerMapper.class)
    public ImmutableList<ImmutableChessPlayer> getChessPlayers();
}
