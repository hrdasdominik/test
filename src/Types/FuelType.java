package Types;

import java.util.stream.Stream;

/**
 * Enum for the fuel types on the vehicles
 */
public enum FuelType {
    DIESEL("diesel"),
    GASOLINE("gasoline"),
    ELECTRICITY("electricity");

    public final String label;

    FuelType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Stream<FuelType> stream() {
        return Stream.of(FuelType.values());
    }

    public static FuelType getByValue(String value) {
        for (FuelType fuelType : values()) {
            if (fuelType.label.equalsIgnoreCase(value)) {
                return fuelType;
            }
        }
        return null;
    }
}
