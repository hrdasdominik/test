package Types;

import java.util.stream.Stream;

/**
 * Enum for the applicable door numbers on the cars
 */
public enum DoorNumber {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7");

    public final String label;

    DoorNumber(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Stream<DoorNumber> stream() {
        return Stream.of(DoorNumber.values());
    }

    public static DoorNumber getByValue(String value) {
        for (DoorNumber doorNumber : values()) {
            if (doorNumber.label.equalsIgnoreCase(value)) {
                return doorNumber;
            }
        }
        return null;
    }

    public static DoorNumber getFirstValue() {
        DoorNumber[] doorNumbers = DoorNumber.values();
        return doorNumbers[0];
    }

    public static DoorNumber getLastValue() {
        DoorNumber[] doorNumbers = DoorNumber.values();
        return doorNumbers[doorNumbers.length - 1];
    }
}
