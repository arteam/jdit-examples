package jdit.testing.dao;

import com.google.common.collect.ImmutableList;
import jdit.testing.domain.ImmutableChessGame;
import jdit.testing.domain.ImmutableChessPlayer;
import jdit.testing.domain.mapper.ChessGameMapper;
import jdit.testing.domain.mapper.ChessPlayerMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

/**
 * Date: 18.03.15
 * Time: 20:54
 *
 * @author Artem Prigoda
 */
@UseClasspathSqlLocator
public interface ChessDao {

    @SqlQuery
    @RegisterRowMapper(ChessGameMapper.class)
    ImmutableList<ImmutableChessGame> getChessGames();

    @SqlQuery
    @RegisterRowMapper(ChessPlayerMapper.class)
    ImmutableList<ImmutableChessPlayer> getChessPlayers();
}
