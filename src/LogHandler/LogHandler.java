package LogHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.*;

/**
 * Class created to log the information about the app activities in the logs.txt file
 */
public class LogHandler {
    private static final String fileName = "logs.txt";
    private static final Logger logger = Logger.getLogger(LogHandler.class.getName());
    private static final Logger rootLogger = Logger.getLogger("");
    private static final FileHandler fileHandler;
    private static FileOutputStream fileOutputStream = null;
    private static final File file;

    static {
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            rootLogger.removeHandler(handler);
        }
        try {
            file = new File(fileName);
            if (!file.exists()) {
                fileOutputStream = new FileOutputStream(fileName);
                fileOutputStream.close();
            }
            fileHandler = new FileHandler(fileName, false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LogHandler() {
    }

    public void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public void logSevere(String message) {
        logger.log(Level.SEVERE, message);
    }
}
