package Controllers;

import ExceptionHandlers.DuplicateVehicleException;
import ExceptionHandlers.EmptyLineException;
import ExceptionHandlers.InvalidDateTimeException;
import ExceptionHandlers.NoSuchChoiceException;
import LogHandler.LogHandler;
import Models.Car;
import Models.Truck;
import Types.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Class that contains all the logic to continues run the app with logic like asking user for input choices
 * in the main menu, etc.
 */
public class Logic {
    private final Scanner userInput;
    private final VehicleManagerImpl vehicleManager;
    private final LogHandler logHandler;

    public Logic() {
        userInput = new Scanner(System.in);
        vehicleManager = new VehicleManagerImpl();
        logHandler = new LogHandler();
    }

    /**
     * Method to be used when instance of the Logic is created to start the app
     * it also logs that the app has started
     */
    public void startApp() {
        logHandler.logInfo("APPLICATION STARTED");
        mainMenu();
    }

    /**
     * Method that prints out the main menu choices
     */
    private void printMainChoices() {
        System.out.print("""
                Vehicle Fleet Management System
                -------------------------------
                1. Add a vehicle to the fleet
                2. Search for vehicles by make or model
                3. Print all vehicles in the fleet
                4. Delete a vehicle from the fleet
                5. Quit
                """);
    }

    /**
     * Method that prints the main menu choices and asks user for input of one of those choices
     */
    private void mainMenu() {
        while (true) {
            printMainChoices();

            int choice = askForIntegerInput("Enter: ");
            try {
                switch (choice) {
                    case 1 -> choiceOne();
                    case 2 -> choiceTwo();
                    case 3 -> choiceThree();
                    case 4 -> choiceFour();
                    case 5 -> {
                        System.out.println("Goodbye!");
                        logHandler.logInfo("APPLICATION CLOSED");
                        return;
                    }
                    default -> throw new NoSuchChoiceException("Wrong input. Please enter one number of the provided choices.");
                }
            } catch (NoSuchChoiceException e) {
                System.out.println(e.getMessage());;
            }
        }
    }

    /**
     * Method that is used for any need of asking user to input a string
     * @param question string that would be displayed to the user to see what kind of info is asked of him to enter
     * @return String - user input
     */
    private String askForStringInput(String question) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(question);
            input = userInput.nextLine();
            try {
                if (input.isEmpty() || input.equals(" ")) {
                    throw new EmptyLineException("Wrong input. Empty input is not allowed!");
                }
            } catch (EmptyLineException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    /**
     * Method that is used for any need of asking user to input a number
     * @param question string that would be displayed to the user to see what kind of info is asked of him to enter
     * @return Integer - user input
     */
    private int askForIntegerInput(String question) {
        int number = -1;
        while (number <= -1) {
            System.out.print(question);
            try {
                number = userInput.nextInt();
                userInput.nextLine();
            } catch (InputMismatchException exception) {
                System.out.println("Wrong input. Input only accepts numbers");
                userInput.nextLine();
            }
        }
        return number;
    }

    /**
     * Starts the logic of 'Add a vehicle to the fleet' where the app asks user for various info to input
     * then it processes it and at the end creates a vehicle of the choice and adds it to the list (fleet)
     */
    private void choiceOne() {
        String choice;
        VehicleType vehicleType = null;
        while (vehicleType == null) {
            System.out.println("\nWhat type of vehicle would you like to add?");
            System.out.println("Choices:");
            VehicleType.stream().forEach(System.out::println);
            System.out.print("Enter: ");
            choice = userInput.nextLine();
            vehicleType = VehicleType.getByValue(choice);
            try {
                if (vehicleType == null) {
                    throw new NoSuchChoiceException("Wrong input. Please enter one word of the provided choices.");
                }
            } catch (NoSuchChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        String manufacturer = askForStringInput("Enter manufacturer: ");
        String model = askForStringInput("Enter model: ");

        Date date = null;
        while (date == null) {
            System.out.print("Enter year: ");
            String dateString = userInput.nextLine();
            try {
                date = new SimpleDateFormat("yyyy").parse(dateString);
                Date presentYear = new Date();
                if (!presentYear.after(date)) {
                    date = null;
                    throw new InvalidDateTimeException("Invalid input. Please enter a year that's not past the present year.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid input. Please enter in format 'yyyy'");
            } catch (InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            }
        }

        String color = askForStringInput("Enter color: ");

        int vin = -1;
        while (vin <= -1) {
            System.out.print("Enter VIN: ");
            try {
                vin = userInput.nextInt();
                userInput.nextLine();
                if (vehicleManager.doesContainVin(vin)) {
                    vin = -1;
                    throw new DuplicateVehicleException("VIN " + vin + " already exists.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Wrong input. Input only accepts numbers");
                userInput.nextLine();
            } catch (DuplicateVehicleException e) {
                System.out.println(e.getMessage());
            }
        }

        FuelType fuelType = null;
        while (fuelType == null) {
            System.out.print("\nFuel types:\n");
            FuelType.stream().forEach(System.out::println);
            System.out.print("Enter: ");
            choice = userInput.nextLine();
            fuelType = FuelType.getByValue(choice);
            try {
                if (fuelType == null) {
                    throw new NoSuchChoiceException("Wrong input. Please enter one of provided choices.");
                }
            } catch (NoSuchChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        if (vehicleType == VehicleType.CAR) {
            DoorNumber doorNumber = null;
            while (doorNumber == null) {
                choice = askForStringInput("Enter number of doors: ");
                doorNumber = DoorNumber.getByValue(choice);
                try {
                    if (doorNumber == null) {
                        throw new NoSuchChoiceException("""
                                Wrong input.
                                Min number of doors is %s
                                Max number of doors is %s""".formatted(DoorNumber.getFirstValue(), DoorNumber.getLastValue())
                        );
                    }
                } catch (NoSuchChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }

            BodyType bodyType = null;
            while (bodyType == null) {
                System.out.println("Choices for body style: ");
                BodyType.stream().forEach(System.out::println);
                choice = askForStringInput("Enter: ");
                bodyType = BodyType.getByValue(choice);
                try {
                    if (bodyType == null) {
                        throw new NoSuchChoiceException("Wrong input. Please enter one of provided choices.");
                    }
                } catch (NoSuchChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }
            Car car = new Car(manufacturer, model, date, color, vin, fuelType, doorNumber, bodyType);
            vehicleManager.addVehicleToFleet(car);
        } else {
            MassUnit massUnit = null;
            while (massUnit == null) {
                System.out.print("Choices for unit mass: ");
                MassUnit.stream().forEach(System.out::println);
                choice = askForStringInput("Enter: ");
                massUnit = MassUnit.getByValue(choice);
                try {
                    if (massUnit == null) {
                        throw new NoSuchChoiceException("Wrong input. Please enter one of provided choices.");
                    }
                } catch (NoSuchChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }

            int cargoCapacityInput = askForIntegerInput("Enter cargo capacity: ");
            String cargoCapacity = String.valueOf(cargoCapacityInput) + massUnit;
            Truck truck = new Truck(manufacturer, model, date, color, vin, fuelType, cargoCapacity);
            vehicleManager.addVehicleToFleet(truck);
        }
        System.out.println("Press enter to continue...");
        userInput.nextLine();
    }

    /**
     * Starts the logic of 'Search for vehicles by make or model' where the app asks user to choose how he wishes
     * to search for the vehicle/s he wants to see information about
     */
    private void choiceTwo() {
        System.out.println("\nWould you lie to search for vehicles by:");
        System.out.println("1. Manufacturer");
        System.out.println("2. Model");
        System.out.println("3. VIN");
        int choice = askForIntegerInput("Enter: ");

        try {
            switch (choice) {
                case 1 -> {
                    String manufacturer = askForStringInput("Enter manufacturer: ");
                    vehicleManager.printAllVehicleByManufacturer(manufacturer);
                    System.out.println("Press enter to continue...");
                    userInput.nextLine();
                }
                case 2 -> {
                    String model = askForStringInput("Enter model: ");
                    vehicleManager.printAllVehicleByModel(model);
                    System.out.println("Press enter to continue...");
                    userInput.nextLine();
                }
                case 3 -> {
                    int vin = askForIntegerInput("Enter VIN: ");
                    vehicleManager.printVehicleByVin(vin);
                    System.out.println("Press enter to continue...");
                    userInput.nextLine();
                }
                default -> throw new NoSuchChoiceException("Wrong input. Please enter one of provided choices.");
            }
        } catch (NoSuchChoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the logic of 'Print all vehicles in the fleet'
     * - displays all the vehicles in the list (fleet) to the user
     */
    private void choiceThree() {
        vehicleManager.printAllVehicles();
        System.out.println("Press enter to continue...");
        userInput.nextLine();
    }

    /**
     * Starts the logic of 'Delete a vehicle from the fleet'
     * - asks the user for the VIN of the vehicle that he wants to delete from the list (fleet)
     */
    private void choiceFour() {
        int vin = askForIntegerInput("Enter VIN: ");
        vehicleManager.removeVehicleFromFleet(vin);
        System.out.println("Press enter to continue...");
        userInput.nextLine();
    }
}
