package jdbi.testing.dao;

import com.google.common.collect.ImmutableList;
import jdbi.testing.domain.ImmutableChessGame;
import jdbi.testing.domain.ChessGame;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

/**
 * Date: 18.03.15
 * Time: 20:54
 *
 * @author Artem Prigoda
 */
public interface ChessDao {

    @SqlQuery("select * from chess_games")
    public ImmutableList<ImmutableChessGame> getChessGames();
}
