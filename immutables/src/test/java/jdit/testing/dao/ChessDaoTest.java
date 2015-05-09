package jdit.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import com.google.common.collect.ImmutableList;
import jdit.testing.domain.ImmutableChessGame;
import jdit.testing.domain.ImmutableChessPlayer;
import jdit.testing.domain.Result;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(DBIRunner.class)
@DataSet({"chessDao/debuts.sql", "chessDao/ranks.sql", "chessDao/players.sql",
        "chessDao/results.sql", "chessDao/games.sql"})
public class ChessDaoTest {

    @TestedSqlObject
    ChessDao chessDao;

    @Test
    public void testGetChessPlayers() throws Exception {
        ImmutableList<ImmutableChessPlayer> chessPlayers = chessDao.getChessPlayers();
        for (ImmutableChessPlayer p : chessPlayers) {
            System.out.println(p);
        }
        assertEquals(chessPlayers.size(), 3);
    }

    @Test
    public void testGetChessGames() {
        ImmutableList<ImmutableChessGame> games = chessDao.getChessGames();
        for (ImmutableChessGame game : games) {
            System.out.println(game);
        }
        assertEquals(games.size(), 3);

        ImmutableChessGame game = games.get(0);
        assertEquals(game.white().lastName(), "Taimanov");
        assertEquals(game.black().lastName(), "Short");
        assertEquals(game.debut().name(), "Benko Gambit, Zaitsev System");
        assertEquals(game.result(), Result.WHITE_WON);
    }
}