package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user tried remove or print a vehicle which is not present in the list
 */
public class NoSuchVehicleException extends RuntimeException {
    private LogHandler logHandler;
    public NoSuchVehicleException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
