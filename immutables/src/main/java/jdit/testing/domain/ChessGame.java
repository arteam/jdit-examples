package jdit.testing.domain;

import org.immutables.value.Value;

/**
 * Date: 18.03.15
 * Time: 20:44
 *
 * @author Artem Prigoda
 */
@Value.Immutable
public abstract class ChessGame {

    @Value.Parameter
    public abstract ChessPlayer white();

    @Value.Parameter
    public abstract ChessPlayer black();

    @Value.Parameter
    public abstract Result result();

    @Value.Parameter
    public abstract Debut debut();
}
