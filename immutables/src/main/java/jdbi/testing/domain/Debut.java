package jdbi.testing.domain;

import org.immutables.value.Value;

/**
 * Date: 18.03.15
 * Time: 20:47
 *
 * @author Artem Prigoda
 */
@Value.Immutable
public abstract class Debut {

    @Value.Parameter
    public abstract String code();

    @Value.Parameter
    public abstract String name();
}
