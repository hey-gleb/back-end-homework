package it.sevenbits.javaformatter.lexer.factory;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.lexer.ILexer;
import it.sevenbits.javaformatter.lexer.LexerException;

/**
 * ILexerInterface for making Lexer classes
 */
public interface ILexerFactory {

    /**
     * Function's contract for creating new Lexer
     *
     * @param reader    - need to read symbols
     * @param lexerType - need to understand what type of lexer needs to return
     * @return new Lexer
     * @throws LexerException is thrown if something goes wrong with lexer
     */
    ILexer createLexer(IReader reader, String lexerType) throws LexerException;
}
