package jdbi.testing.domain;

import org.immutables.value.Value;

/**
 * Date: 18.03.15
 * Time: 20:44
 *
 * @author Artem Prigoda
 */
@Value.Immutable
public abstract class ChessGame {

    public abstract ChessPlayer white();

    public abstract ChessPlayer black();

    public abstract Result result();

    public abstract Debut debut();
}
