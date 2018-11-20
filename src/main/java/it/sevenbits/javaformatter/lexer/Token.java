package it.sevenbits.javaformatter.lexer;

/**
 * Implementation of IToken interface
 */
public class Token implements IToken {
    private String name;
    private String lexeme;

    /**
     * Token constructor with 2 parameters
     *
     * @param name   - token's name
     * @param lexeme - character sequence
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    /**
     * Token constructor with 1 parameter
     *
     * @param lexeme - character sequence
     */
    public Token(final String lexeme) {
        this.name = "DEFAULT";
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLexeme() {
        return this.lexeme;
    }
}
