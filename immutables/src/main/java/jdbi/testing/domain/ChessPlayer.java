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

    @Value.Parameter
    public abstract String firstName();

    @Value.Parameter
    public abstract String lastName();

    @Value.Parameter
    public abstract Rank rank();
}
