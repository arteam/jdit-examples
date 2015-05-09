package testing;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DBIHandle;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedSqlObject;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import jdit.testing.Player;
import jdit.testing.PlayerDao;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skife.jdbi.v2.Handle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Date: 2/14/15
 * Time: 11:47 PM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
@DataSet("playerDao/players.sql")
public class PlayerDaoTest {

    @TestedSqlObject
    PlayerDao playerDao;

    @DBIHandle
    Handle handle;

    @Test
    public void testCreatePlayer() {
        Long playerId = playerDao.createPlayer("Vladimir", "Tarasenko", date("1991-12-13"), 184, 90);
        List<Map<String, Object>> rows = handle.select("select * from players where id=?", playerId);
        assertFalse(rows.isEmpty());

        Map<String, Object> row = rows.get(0);
        assertEquals(4, row.get("id"));
        assertEquals("Vladimir", row.get("first_name"));
        assertEquals("Tarasenko", row.get("last_name"));
        assertEquals(date("1991-12-13"), row.get("birth_date"));
        assertEquals(184, row.get("height"));
        assertEquals(90, row.get("weight"));
    }

    @Test
    public void testGetPlayerListNames() {
        List<String> playerLastNames = playerDao.getPlayerLastNames();
        assertEquals(playerLastNames, ImmutableList.of("Ellis", "Seguin", "Tarasenko", "Tavares"));
    }

    @Test
    public void testGetAmountPlayersBornInYear() {
        int amount = playerDao.getAmountPlayersBornInYear(1991);
        assertEquals(amount, 2);
    }

    @Test
    public void testSuccessfulPlayerSearch(){
        Optional<Player> player = playerDao.findPlayer("Ryan", "Ellis");
        assertTrue(player.isPresent());
        assertEquals(player.get().firstName, "Ryan");
        assertEquals(player.get().lastName, "Ellis");
    }

    @Test
    public void testUnsuccessfulPlayerSearch(){
        Optional<Player> player = playerDao.findPlayer("Jake", "Johnson");
        assertFalse(player.isPresent());
    }

    private static Date date(String textDate) {
        return ISODateTimeFormat.date().parseDateTime(textDate).toDate();
    }
}
