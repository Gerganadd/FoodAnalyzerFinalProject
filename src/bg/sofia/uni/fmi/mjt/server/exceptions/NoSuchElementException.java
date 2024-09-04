package bg.sofia.uni.fmi.mjt.server.exceptions;

public class NoSuchElementException extends Exception {
    public NoSuchElementException(String message) {
        super(message);
    }

    public NoSuchElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
