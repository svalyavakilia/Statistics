package statistics;

public class NotEnoughDataException extends Exception {
    public NotEnoughDataException(final String message) {
        super(message);
    }
}