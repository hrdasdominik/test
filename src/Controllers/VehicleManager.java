package Controllers;


import Models.Vehicle;

/**
 * Controller for the management of the list of vehicles
 */
public interface VehicleManager {
    void addVehicleToFleet(Vehicle vehicle);
    void printAllVehicleByManufacturer(String manufacturer);
    void printAllVehicleByModel(String model);
    void printVehicleByVin(int vin);
    void printAllVehicles();
    void removeVehicleFromFleet(int vin);
    boolean doesContainVin(int vin);
}
