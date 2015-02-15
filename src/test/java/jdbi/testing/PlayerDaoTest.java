package jdbi.testing;

import com.github.arteam.dropwizard.testing.jdbi.DBIRunner;
import com.github.arteam.dropwizard.testing.jdbi.annotations.DBIHandle;
import com.github.arteam.dropwizard.testing.jdbi.annotations.DataSet;
import com.github.arteam.dropwizard.testing.jdbi.annotations.TestedSqlObject;
import com.google.common.collect.ImmutableList;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skife.jdbi.v2.Handle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
        List<Map<String,Object>> rows = handle.select("select * from players where id=?", playerId);
        assertFalse(rows.isEmpty());

        Map<String, Object> row = rows.get(0);
        assertEquals(0, row.get("id"));
        assertEquals("Vladimir", row.get("first_name"));
        assertEquals("Tarasenko", row.get("last_name"));
        assertEquals(date("1991-12-13"), row.get("birth_date"));
        assertEquals(184, row.get("height"));
        assertEquals(90, row.get("weight"));
    }

@Test
public void testGetPlayerListNames(){
    List<String> playerLastNames = playerDao.getPlayerLastNames();
    assertEquals(playerLastNames, ImmutableList.of("Ellis", "Seguin", "Tarasenko", "Tavares"));
}

    private static Date date(String textDate) {
        return ISODateTimeFormat.date().parseDateTime(textDate).toDate();
    }
}
