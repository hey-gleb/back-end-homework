package it.sevenbits.javaformatter.lexer;

/**
 * Exception class, thrown when something goes wrong with lexeme
 */
public class LexerException extends Exception {
//    /**
//     * LexerException's constructor
//     */
//    public LexerException() {
//    }
//
//    /**
//     * LexerException's constructor with one parameter
//     *
//     * @param message - error message
//     */
//    public LexerException(final String message) {
//        super(message);
//    }

    /**
     * LexerException's constructor with two parameters
     *
     * @param message - error message
     * @param cause   - exception, needs to keep stacktrace
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
