package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.ReaderException;

/**
 * Interface with function to read tokens from stream and check for more tokens
 */
public interface ILexer {
    /**
     * Create token from character sequence
     *
     * @return new token
     * @throws ReaderException is thrown, if something goes wrong with reading character
     * @throws LexerException  is thrown, if something wrong with lexeme
     */
    IToken readToken() throws ReaderException, LexerException;

    /**
     * Checks is there another one token or not
     *
     * @return true if there is another token, false - not
     * @throws ReaderException is thrown, if something goes wrong with reading characters
     */
    boolean hasMoreTokens() throws ReaderException;
}
