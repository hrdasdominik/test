package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user tried to remove or print vehicles from the map which is empty
 */
public class MapIsEmptyException extends Exception {
    private LogHandler logHandler;
    public MapIsEmptyException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
