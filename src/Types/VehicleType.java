package Types;

import java.util.stream.Stream;

/**
 * Enum for the vehicle types
 */
public enum VehicleType {
    CAR("car"),
    TRUCK("truck");

    private final String label;

    VehicleType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Stream<VehicleType> stream() {
        return Stream.of(VehicleType.values());
    }

    public static VehicleType getByValue(String value) {
        for (VehicleType vehicleType : values()) {
            if (vehicleType.label.equalsIgnoreCase(value)) {
                return vehicleType;
            }
        }
        return null;
    }
}
