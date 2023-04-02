package Types;

import java.util.stream.Stream;

/**
 * Enum for the body types/styles of the cars
 */
public enum BodyType {
    BUGGY("buggy"),
    CONVERTIBLE("convertible"),
    COUPE("coupe"),
    FLOWER_CAR("flower car"),
    HATCHBACK("hatchback"),
    HEARSE("hearse"),
    MICROVAN("microvan"),
    MINIVAN("minivan"),
    PANEL_VAN("panel van"),
    PANEL_TRUCK("panel truck"),
    PICKUP_TRUCK("pickup truck"),
    ROADSTER("roadster"),
    SEDAN("sedan"),
    SHOOTING_BRAKE("shooting brake"),
    TARGA_TOP("targa_top"),
    UTE("ute");

    public final String label;

    BodyType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Stream<BodyType> stream() {
        return Stream.of(BodyType.values());
    }

    public static BodyType getByValue(String value) {
        for (BodyType bodyType : values()) {
            if (bodyType.label.equalsIgnoreCase(value)) {
                return bodyType;
            }
        }
        return null;
    }
}
