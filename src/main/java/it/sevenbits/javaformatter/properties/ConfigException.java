package it.sevenbits.javaformatter.properties;

/**
 * Config exception class
 */
public class ConfigException extends Exception {

    /**
     * ConfigException constructor with no parameters
     *
     * @param message - error message
     */
    public ConfigException(final String message) {
        super(message);
    }

    /**
     * ConfigException constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public ConfigException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * ConfigException constructor with one parameters
     *
     * @param cause - exception, needs to keep stacktrace
     */
    public ConfigException(final Throwable cause) {
        super(cause);
    }
}
