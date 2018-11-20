package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.IReader;

/**
 * ILexerInterface for making Lexer classes
 */
public interface ILexerFactory {

    /**
     * Function's contract for creating new Lexer
     *
     * @param reader - need to read symbols
     * @return new Lexer
     */
    ILexer createLexer(IReader reader);

}
