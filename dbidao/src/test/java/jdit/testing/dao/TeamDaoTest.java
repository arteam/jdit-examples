package jdit.testing.dao;

import com.github.arteam.jdit.DBIRunner;
import com.github.arteam.jdit.annotations.DBIHandle;
import com.github.arteam.jdit.annotations.DataSet;
import com.github.arteam.jdit.annotations.TestedDao;
import jdit.testing.domain.Division;
import jdit.testing.domain.Player;
import jdit.testing.domain.Position;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.jdbi.v3.core.Handle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

/**
 * Date: 2/21/15
 * Time: 1:01 AM
 *
 * @author Artem Prigoda
 */
@RunWith(DBIRunner.class)
public class TeamDaoTest {

    @TestedDao
    TeamDao teamDao;

    @DBIHandle
    Handle handle;

    @Test
    @DataSet("teamDao/divisions.sql")
    public void testAddTeam() {
        teamDao.addTeam("St. Louis Blues", Division.CENTRAL, ImmutableList.of(
                new Player("David", "Backes", 42, Position.CENTER),
                new Player("TJ", "O'Shie", 74, Position.RIGHT_WING)
        ));
        List<Map<String, Object>> rows = handle.createQuery("select * from teams where name=?")
                .bind(0, "St. Louis Blues")
                .mapToMap()
                .list();
        Assert.assertEquals(rows.size(), 1);
        Long teamId = (Long) rows.get(0).get("id");
        List<Map<String, Object>> rosterRows = handle.createQuery("select * from roster where team_id=?")
                .bind(0, teamId)
                .mapToMap()
                .list();
        Assert.assertEquals(rosterRows.size(), 2);
    }
}
