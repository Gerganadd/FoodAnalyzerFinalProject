package bg.sofia.uni.fmi.mjt.server.logs;

import bg.sofia.uni.fmi.mjt.server.database.FileManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
    private static final String DEFAULT_FILE_NAME = "ServerLogs.txt";
    private static final String DEFAULT_EXCEPTIONS_FILE_NAME = "ServerExceptionsLogs.txt";
    private static final String LOGS_FORMAT = "%s - %s: %s \n";
    private static final String EXCEPTIONS_LOGS_FORMAT = "%s - %s: %s, stackTreatise: %s \n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

    private static Logger instance;
    private List<String> logs = new ArrayList<>();
    private List<String> exceptionsLogs = new ArrayList<>();

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void addException(Status status, String message, Exception exception) {
        List<StackTraceElement> stackTrace = Arrays.stream(exception.getStackTrace()).toList();
        String time = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        exceptionsLogs.add(String.format(EXCEPTIONS_LOGS_FORMAT,
                time, status, message, stackTrace));
    }

    public void addLog(Status status, String message) {
        String time = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        logs.add(String.format(LOGS_FORMAT,
                time, status, message));
    }

    public void saveLogs(String filename) {
        if (filename == null || filename.isBlank()) {
            filename = DEFAULT_FILE_NAME;
        }

        FileManager.saveTo(logs, filename);
    }

    public void saveLogs() {
        saveLogs(DEFAULT_FILE_NAME);
    }

    public void saveExceptions(String filename) {
        if (filename == null || filename.isBlank()) {
            filename = DEFAULT_EXCEPTIONS_FILE_NAME;
        }

        FileManager.saveTo(exceptionsLogs, filename);
    }

    public void saveExceptions() {
        saveExceptions(DEFAULT_EXCEPTIONS_FILE_NAME);
    }
}
