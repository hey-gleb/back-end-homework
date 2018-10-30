package it.sevenbits.exceptions;

/**
 * Exception class, thrown when something goes wrong with reading
 */
public class ReaderException extends Exception {
    /**
     * ReaderException's constructor
     */
    public ReaderException() {
    }

    /**
     * ReaderException's constructor with one parameter
     *
     * @param message - error message
     */
    public ReaderException(final String message) {
        super(message);
    }

    /**
     * ReaderException's constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
