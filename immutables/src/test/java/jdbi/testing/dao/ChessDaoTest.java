package jdbi.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import com.google.common.collect.ImmutableList;
import jdbi.testing.domain.ImmutableChessGame;
import jdbi.testing.domain.ImmutableChessPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(DBIRunner.class)
public class ChessDaoTest {

    @TestedSqlObject
    ChessDao chessDao;

    @Test
    @DataSet({"chessDao/debuts.sql", "chessDao/ranks.sql", "chessDao/players.sql", "chessDao/results.sql"})
    public void testGetChessGames() throws Exception {
        ImmutableList<ImmutableChessPlayer> chessPlayers = chessDao.getChessPlayers();
        System.out.println(chessPlayers);
        assertEquals(chessPlayers.size(), 3);
    }
}