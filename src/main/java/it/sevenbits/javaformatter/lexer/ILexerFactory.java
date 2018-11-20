package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;

/**
 * ILexerInterface for making Lexer classes
 */
public interface ILexerFactory {

    /**
     * Function's contract for creating new Lexer
     *
     * @param reader
     * @return new Lexer
     * @param reader - need to read symbols
     * @throws ReaderException is thrown, if something goes wrong
     */
    ILexer createLexer(IReader reader);

}
