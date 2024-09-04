package bg.sofia.uni.fmi.mjt.server.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
