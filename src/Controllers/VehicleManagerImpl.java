package Controllers;

import ExceptionHandlers.MapIsEmptyException;
import ExceptionHandlers.NoSuchVehicleException;
import LogHandler.LogHandler;
import Models.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements controller for the management of the vehicles in the list (fleet)
 */
public class VehicleManagerImpl implements VehicleManager {
    private final Map<Integer, Vehicle> fleet;
    private final LogHandler logHandler;

    public VehicleManagerImpl() {
        fleet = new HashMap<>();
        logHandler = new LogHandler();
    }

    /**
     * Adds vehicle to the map with the VIN as it's key and the Vehicle object as it's value
     * @param vehicle - instance of the Vehicle class to be added to the map
     */
    @Override
    public void addVehicleToFleet(Vehicle vehicle) {
        int vin = vehicle.getVin();
        fleet.put(vin, vehicle);
        System.out.println("Vehicle " + vin + " was successfully added to the fleet\n");
        logHandler.logInfo("Vehicle " + vin + " was successfully added to the fleet\n" + vehicle);
    }

    /**
     * Prints all the vehicles found in the map filtered by the manufacturer, if they exist
     * @param manufacturer - self-explanatory
     */
    @Override
    public void printAllVehicleByManufacturer(String manufacturer) {
        List<Vehicle> vehiclesByManufacturer = fleet.values().stream()
                .filter(vehicle -> vehicle.getManufacturer().equalsIgnoreCase(manufacturer))
                .toList();
        try {
            if (vehiclesByManufacturer.isEmpty()) {
                throw new NoSuchVehicleException("No vehicle exists with model " + manufacturer);
            } else {
                vehiclesByManufacturer.forEach(System.out::println);
            }
        } catch (NoSuchVehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the vehicles found in the map filtered by the model, if they exist
     * @param model - self-explanatory
     */
    @Override
    public void printAllVehicleByModel(String model) {
        List<Vehicle> vehiclesByModel = fleet.values().stream()
                .filter(vehicle -> vehicle.getModel().equalsIgnoreCase(model))
                .toList();
        try {
            if (vehiclesByModel.isEmpty()) {
                throw new NoSuchVehicleException("No vehicle exists with model " + model);
            } else {
                vehiclesByModel.forEach(System.out::println);
            }
        } catch (NoSuchVehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all the vehicles found in the map filtered by the VIN, if they exist
     * @param vin - self-explanatory
     */
    @Override
    public void printVehicleByVin(int vin) {
        try {
            if (fleet.containsKey(vin)) {
                System.out.println(fleet.get(vin));
            } else {
                throw new NoSuchVehicleException("Vehicle " + vin + " not found!");
            }
        } catch (NoSuchVehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays all the vehicles inside the map, if any exist
     */
    @Override
    public void printAllVehicles() {
        try {
            if (fleet.isEmpty()) {
                throw new MapIsEmptyException("No vehicles in the fleet.");
            } else {
                fleet.values().forEach(System.out::println);
            }
        } catch (MapIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the vehicle from the map finding it by VIN (Key), if it exists
     * @param vin
     */
    @Override
    public void removeVehicleFromFleet(int vin) {
        try {
            if (!fleet.containsKey(vin)) {
                throw new NoSuchVehicleException("Vehicle " + vin + " not found!");
            } else {
                Vehicle vehicle = fleet.get(vin);
                fleet.remove(vin);
                System.out.println("Vehicle " + vin + " removed from the fleet.");
                logHandler.logInfo("Safely removed vehicle:\n" + vehicle);
            }
        } catch (NoSuchVehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks if the map contains a vehicle under the VIN key
     * @param vin - self-explanatory
     * @return Boolean
     */
    @Override
    public boolean doesContainVin(int vin) {
        return fleet.containsKey(vin);
    }
}
