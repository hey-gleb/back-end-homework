package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;

public class Lexer implements ILexer {
    private IReader reader;
    private StringBuilder stringBuilder;
    private LexerMap lexerMap;

    public Lexer(final IReader reader) {
        this.reader = reader;
        this.lexerMap = new LexerMap();
    }

    @Override
    public IToken readToken() throws ReaderException {
        stringBuilder = new StringBuilder();
        char currentChar;
        while (reader.hasNext()) {
            currentChar = reader.read();
            stringBuilder.append(currentChar);
            if (lexerMap.getKey(stringBuilder.toString()) != null) {
                return new Token(lexerMap.getKey(stringBuilder.toString()), stringBuilder.toString());
            }
            if (currentChar == ' ') {
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
