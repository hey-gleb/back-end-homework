package it.sevenbits.javaformatter.formatter;

/**
 * Formatter exception class
 */
public class FormatterException extends Exception {

    /**
     * FormatterException constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public FormatterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
