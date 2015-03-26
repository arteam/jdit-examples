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

    @SqlQuery("select cg.id, r.name result_name, d.code debut_code, d.name debut_name, " +
            "wcp.first_name as white_player_first_name, wcp.last_name as white_player_last_name, wcp.rank_name white_player_rank, " +
            "bcp.first_name as black_player_first_name, bcp.last_name as black_player_last_name, bcp.rank_name black_player_rank " +
            "from chess_games cg " +
            "inner join chess_players wcp on cg.white_player_id=wcp.id " +
            "inner join chess_players bcp on cg.black_player_id=bcp.id " +
            "inner join debuts d on cg.debut_code=d.code " +
            "inner join results r on cg.result_id=r.id " +
            "order by cg.id")
    @Mapper(ChessGameMapper.class)
    public ImmutableList<ImmutableChessGame> getChessGames();

    @SqlQuery("select first_name as player_first_name, last_name as player_last_name," +
            " rank_name player_rank from chess_players")
    @Mapper(ChessPlayerMapper.class)
    public ImmutableList<ImmutableChessPlayer> getChessPlayers();
}
