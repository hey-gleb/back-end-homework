package it.sevenbits.javaformatter.lexer.token;

/**
 * IToken interface for working with token
 */
public interface IToken {

    /**
     * Function for getting token's name
     *
     * @return token's name
     */
    String getName();

    /**
     * Function for getting token's lexeme
     *
     * @return token's lexeme
     */
    String getLexeme();
}
