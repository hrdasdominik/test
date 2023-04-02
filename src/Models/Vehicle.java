package Models;

import Types.FuelType;

import java.util.Date;

/**
 * Abstract class used as a base skeleton for the vehicle objects like cars, trucks, etc.
 */
public abstract class Vehicle {
    protected String manufacturer;
    protected String model;
    protected Date yearOfManufacture;
    protected String color;
    protected int vin;
    protected FuelType fuelType;

    public Vehicle(
            String manufacturer,
            String model,
            Date yearOfManufacture,
            String color,
            int vin,
            FuelType fuelType
    ) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.vin = vin;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", color='" + color + '\'' +
                ", vin=" + vin +
                ", fuelType=" + fuelType +
                '}';
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Date getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public int getVin() {
        return vin;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYearOfManufacture(Date yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setVin(String vin) {
        this.vin = Integer.parseInt(vin);
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
