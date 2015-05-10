package jdit.testing.dao;

import com.google.common.collect.ImmutableList;
import jdit.testing.domain.ImmutableChessGame;
import jdit.testing.domain.ImmutableChessPlayer;
import jdit.testing.domain.mapper.ChessGameMapper;
import jdit.testing.domain.mapper.ChessPlayerMapper;
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
    ImmutableList<ImmutableChessGame> getChessGames();

    @SqlQuery("chessDao/get-chess-players.sql")
    @Mapper(ChessPlayerMapper.class)
    ImmutableList<ImmutableChessPlayer> getChessPlayers();
}
