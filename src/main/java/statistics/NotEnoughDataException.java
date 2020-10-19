package statistics;

/**
 * This class represents a runtime exception which indicates
 * that there is not enough data to analyse.
 *
 * @author svalyavakilia
 */
public class NotEnoughDataException extends Exception {
    /**
     * Constructor for NotEnoughDataException class instance.
     *
     * @param message message of a constructed exception.
     */
    public NotEnoughDataException(final String message) {
        super(message);
    }
}