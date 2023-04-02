package Models;

import Types.BodyType;
import Types.DoorNumber;
import Types.FuelType;

import java.util.Date;

/**
 * Model class that represents car object
 */
public class Car extends Vehicle {
    DoorNumber doorNumber;
    BodyType bodyType;

    public Car(String manufacturer, String model, Date yearOfManufacture, String color, int vin, FuelType fuelType, DoorNumber doorNumber, BodyType bodyType) {
        super(manufacturer, model, yearOfManufacture, color, vin, fuelType);
        this.doorNumber = doorNumber;
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", vin=" + vin +
                ", fuelType=" + fuelType +
                ", doorNumber=" + doorNumber +
                ", bodyType=" + bodyType +
                '}';
    }

    public DoorNumber getDoorNumber() {
        return doorNumber;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setDoorNumber(DoorNumber doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
}
