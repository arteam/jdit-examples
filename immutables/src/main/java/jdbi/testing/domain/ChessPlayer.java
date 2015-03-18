package jdbi.testing.domain;

import org.immutables.value.Value;

/**
 * Date: 18.03.15
 * Time: 20:44
 *
 * @author Artem Prigoda
 */
@Value.Immutable
public abstract class ChessPlayer {

    public abstract String firstName();

    public abstract String lastName();

    public abstract Rank rank();
}
