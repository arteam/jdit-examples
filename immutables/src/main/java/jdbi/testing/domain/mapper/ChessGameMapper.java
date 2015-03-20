package jdbi.testing.domain.mapper;

import jdbi.testing.domain.ImmutableChessGame;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date: 18.03.15
 * Time: 20:59
 *
 * @author Artem Prigoda
 */
public class ChessGameMapper implements ResultSetMapper<ImmutableChessGame> {

    private final ChessPlayerMapper chessPlayerMapper = new ChessPlayerMapper();
    private final DebutMapper debutMapper = new DebutMapper();

    @Override
    public ImmutableChessGame map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return ImmutableChessGame.builder()
                .white(chessPlayerMapper.map("white_", r))
                .black(chessPlayerMapper.map("black_", r))
                .debut(debutMapper.map(index, r, ctx))
                .build();
    }
}
