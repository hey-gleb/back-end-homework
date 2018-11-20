package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.IReader;

/**
 * Implementation of ILexer interface
 */
public class Lexer implements ILexer {
    private IReader reader;
    private StringBuilder stringBuilder;
    private LexerMap lexerMap;

    /**
     * Lexer constructor with one parameter
     *
     * @param reader - need to read symbols
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        this.lexerMap = new LexerMap();
    }

    @Override
    public IToken readToken() throws LexerException {
        stringBuilder = new StringBuilder();
        char currentChar;
        try {
            while (reader.hasNext()) {
                currentChar = reader.read();
                stringBuilder.append(currentChar);
                if (lexerMap.getKey(stringBuilder.toString()) != null) {
                    return new Token(lexerMap.getKey(stringBuilder.toString()), stringBuilder.toString());
                } else {
                    return new Token(stringBuilder.toString());
                }
            }
            return null;
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Override
    public boolean hasMoreTokens() throws LexerException {
        try {
            return reader.hasNext();
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with checking has next or not", e);
        }
    }
}
