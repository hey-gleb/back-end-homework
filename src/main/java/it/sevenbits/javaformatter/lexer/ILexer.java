package it.sevenbits.javaformatter.lexer;

/**
 * Interface with function to read tokens from stream and check for more tokens
 */
public interface ILexer {
    /**
     * Create token from character sequence
     *
     * @return new token
     * @throws LexerException is thrown, if something wrong with lexeme
     */
    IToken readToken() throws LexerException;

    /**
     * Checks is there another one token or not
     *
     * @return true if there is another token, false - not
     * @throws LexerException is thrown, if something wrong with lexeme
     */
    boolean hasMoreTokens() throws LexerException;
}
