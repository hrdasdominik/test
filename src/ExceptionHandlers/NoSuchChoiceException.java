package ExceptionHandlers;

import LogHandler.LogHandler;

/**
 * Class for throwing and logging exception if the user inputted a choice which is not present or implemented in the app
 */
public class NoSuchChoiceException extends Exception {
    private LogHandler logHandler;
    public NoSuchChoiceException(String errorMessage) {
        super("\n" + errorMessage + "\n");
        logHandler = new LogHandler();
        logHandler.logWarning(errorMessage);
    }
}
