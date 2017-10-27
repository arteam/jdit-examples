package jdit.testing.domain.mapper;

import jdit.testing.domain.ImmutableChessGame;
import jdit.testing.domain.Result;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:59
 *
 * @author Artem Prigoda
 */
public class ChessGameMapper implements RowMapper<ImmutableChessGame> {

    private final ChessPlayerMapper chessPlayerMapper = new ChessPlayerMapper();
    private final DebutMapper debutMapper = new DebutMapper();

    @Override
    public ImmutableChessGame map(ResultSet r, StatementContext ctx) throws SQLException {
        return ImmutableChessGame.builder()
                .white(chessPlayerMapper.map("white_", r))
                .black(chessPlayerMapper.map("black_", r))
                .debut(debutMapper.map(r, ctx))
                .result(Result.valueOf(r.getString("result_name").toUpperCase()))
                .build();
    }
}
