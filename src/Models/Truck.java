package Models;

import Types.FuelType;

import java.util.Date;

/**
 * Model class that represents truck object
 */
public class Truck extends Vehicle {
    String cargoCapacity;

    public Truck(String manufacturer, String model, Date yearOfManufacture, String color, int vin, FuelType fuelType, String cargoCapacity) {
        super(manufacturer, model, yearOfManufacture, color, vin, fuelType);
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", vin=" + vin +
                ", fuelType=" + fuelType +
                ", cargoCapacity='" + cargoCapacity + '\'' +
                '}';
    }

    public String getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(String cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
}
