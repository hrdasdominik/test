package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user tried to input empty string
 */
public class EmptyLineException extends Exception {
    private final LogHandler logHandler;
    public EmptyLineException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
