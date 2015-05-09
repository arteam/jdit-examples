package jdit.testing.domain;

/**
 * Date: 2/21/15
 * Time: 12:36 AM
 *
 * @author Artem Prigoda
 */
public enum Position {

    GOALTENDER("G"),
    DEFENDER("D"),
    CENTER("C"),
    LEFT_WING("LW"),
    RIGHT_WING("RW");

    private static final Position[] VALUES = values();
    public String code;

    private Position(String code) {
        this.code = code;
    }

    public static Position getByCode(String code) {
        for (Position position : VALUES) {
            if (position.code.equalsIgnoreCase(code)) {
                return position;
            }
        }
        return null;
    }
}
