package it.sevenbits.javaformatter;

/**
 * Exception class, thrown when something goes wrong with writing
 */
public class WriterException extends Exception {

    /**
     * WriterException's constructor
     */
    public WriterException() {
    }

    /**
     * WriterException's constructor with one parameter
     *
     * @param message - error message
     */
    public WriterException(final String message) {
        super(message);
    }

    /**
     * WriterException's constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public WriterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
