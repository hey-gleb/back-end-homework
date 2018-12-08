package it.sevenbits.javaformatter.lexer;

/**
 * Class contains lexeme and boolean to check is it done or not
 */
public class Lexeme implements ILexeme {
    private StringBuilder lexeme;
    private boolean isDone;

    /**
     * Lexeme constructor with no parameters
     */
    public Lexeme() {
        lexeme = new StringBuilder();
        isDone = false;
    }

    @Override
    public void append(final char symbol) {
        lexeme.append(symbol);
    }

    @Override
    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public void setIsDone(final boolean done) {
        isDone = done;
    }

    @Override
    public Object getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return lexeme.toString();
    }
}
