package jdbi.testing;

import com.google.common.base.Optional;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;

import java.util.Date;
import java.util.List;

/**
 * Date: 2/13/15
 * Time: 11:37 PM
 *
 * @author Artem Prigoda
 */
public interface PlayerDao {

    @GetGeneratedKeys
    @SqlUpdate("insert into players(first_name, last_name, birth_date, weight, height)"
            + " values (:first_name, :last_name, :birth_date, :weight, :height)")
    Long createPlayer(@Bind("first_name") String firstName,
                      @Bind("last_name") String lastName,
                      @Bind("birth_date") Date birthDate,
                      @Bind("height") int height, @Bind("weight") int weight);

    @SqlQuery("select last_name from players order by last_name")
    List<String> getPlayerLastNames();

    @SqlQuery("select count(*) from players where year(birth_date) = :year")
    int getAmountPlayersBornInYear(@Bind("year") int year);

    @SqlQuery("select * from players where first_name=:first_name and " +
            "last_name=:last_name")
    @SingleValueResult
    @Mapper(PlayerMapper.class)
    Optional<Player> findPlayer(@Bind("first_name") String firstName,
                                @Bind("last_name") String lastName);
}
