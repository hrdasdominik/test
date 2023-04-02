package Types;

import java.util.stream.Stream;

/**
 * Enum for the unit for the mass which use trucks
 */
public enum MassUnit {
    KILOGRAM("kg"),
    TON("t"),
    POUND("lbs");


    private final String label;

    MassUnit(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Stream<MassUnit> stream() {
        return Stream.of(MassUnit.values());
    }

    public static MassUnit getByValue(String value) {
        for (MassUnit massUnit : values()) {
            if (massUnit.label.equalsIgnoreCase(value)) {
                return massUnit;
            }
        }
        return null;
    }
}
