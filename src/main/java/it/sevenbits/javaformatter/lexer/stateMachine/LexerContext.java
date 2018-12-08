package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.lexer.ILexeme;
import it.sevenbits.javaformatter.lexer.Lexeme;

/**
 * Class describes lexer context for commands
 */
public class LexerContext {
    private ILexeme lexeme;
    private char symbol;

    /**
     * LexerContext constructor with no parameters
     */
    public LexerContext() {
        lexeme = new Lexeme();
        symbol = (int) 0;
    }

    public void setLexeme(final ILexeme lexeme) {
        this.lexeme = lexeme;
    }

    public ILexeme getLexeme() {
        return lexeme;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(final char symbol) {

        this.symbol = symbol;
    }
}
