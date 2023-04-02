package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user inputted year that is past the present year
 */
public class InvalidDateTimeException extends Exception {
    private LogHandler logHandler;
    public InvalidDateTimeException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
