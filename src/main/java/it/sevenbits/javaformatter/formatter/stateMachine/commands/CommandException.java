package it.sevenbits.javaformatter.formatter.stateMachine.commands;

/**
 * Exception class, thrown when something goes wrong with executing command
 */
public class CommandException extends Exception {

    /**
     * CommandException constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needed to keep stacktrace
     */
    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
