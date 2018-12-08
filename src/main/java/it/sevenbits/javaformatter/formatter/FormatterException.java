package it.sevenbits.javaformatter.formatter;

/**
 * Formatter exception class
 */
public class FormatterException extends Exception {

    /**
     * FormatterException constructor with one parameter
     *
     * @param message - error message
     */
    public FormatterException(final String message) {
        super(message);
    }

    /**
     * FormatterException constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public FormatterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * FormatterException constructor with one parameters
     *
     * @param cause - exception, needs to keep stacktrace
     */
    public FormatterException(final Throwable cause) {
        super(cause);
    }
}
