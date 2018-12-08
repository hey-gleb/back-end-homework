package it.sevenbits.javaformatter.lexer;

/**
 * ILexeme interface for collecting lexeme
 */
public interface ILexeme {

    /**
     * Function appends symbol to current lexeme
     *
     * @param symbol - symbol for appending
     */
    void append(char symbol);

    /**
     * Function return current lexeme
     *
     * @return current lexeme
     */
    Object getLexeme();

    /**
     * Function gets and returns value is done or not
     *
     * @return current value is done or not
     */
    boolean getIsDone();

    /**
     * Function sets value is done or not
     *
     * @param isDone value for boolean value
     */
    void setIsDone(boolean isDone);
}
