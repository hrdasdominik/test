package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user tried to add the vehicle VIN that already exists in the list (fleet)
 */
public class DuplicateVehicleException extends Exception {
    private LogHandler logHandler;
    public DuplicateVehicleException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
