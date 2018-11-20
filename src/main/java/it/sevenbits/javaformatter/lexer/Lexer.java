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
    public IToken readToken() throws ReaderException, LexerException {
        stringBuilder = new StringBuilder();
        char currentChar;
        while (reader.hasNext()) {
            currentChar = reader.read();
//            if (lexerMap.isSymbolSeparator(currentChar) && !isSeparator) {
//                lastSymbol = currentChar;
//                isSeparator = true;
//                return new Token(stringBuilder.toString());
//            }
            stringBuilder.append(currentChar);
            if (lexerMap.getKey(stringBuilder.toString()) != null) {
                return new Token(lexerMap.getKey(stringBuilder.toString()), stringBuilder.toString());
            } else {
                return new Token(stringBuilder.toString());
            }
        }
        return null;
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasNext();
    }
}
