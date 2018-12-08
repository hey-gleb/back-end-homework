package it.sevenbits.javaformatter.lexer.factory;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.lexer.ILexer;
import it.sevenbits.javaformatter.lexer.Lexer;
import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.lexer.LexerStateMachine;

/**
 * Implementation of ILexerFactory interface
 */
public class LexerFactory implements ILexerFactory {

    @Override
    public ILexer createLexer(final IReader reader, final String lexerType) throws LexerException {
        switch (lexerType) {
            case "LEXER":
                return new Lexer(reader);
            case "LEXER_STATE_MACHINE":
                return new LexerStateMachine(reader);
            default:
                return null;
        }
    }
}
