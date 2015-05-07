package jdbi.testing.dao;

import com.google.common.collect.ImmutableList;
import jdbi.testing.domain.ImmutableChessGame;
import jdbi.testing.domain.ImmutableChessPlayer;
import jdbi.testing.domain.mapper.ChessGameMapper;
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

    @SqlQuery("chessDao/get-chess-games.sql")
    @Mapper(ChessGameMapper.class)
    public ImmutableList<ImmutableChessGame> getChessGames();

    @SqlQuery("chessDao/get-chess-players.sql")
    @Mapper(ChessPlayerMapper.class)
    public ImmutableList<ImmutableChessPlayer> getChessPlayers();
}
