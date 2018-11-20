package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.interfaces.IReader;

/**
 * Implementation of ILexerFactory interface
 */
public class LexerFactory implements ILexerFactory {

    /**
     * LexerFactory constructor with no parameters
     */
    public LexerFactory() {

    }

    @Override
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
