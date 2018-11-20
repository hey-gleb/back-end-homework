package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.exceptions.LexerException;
import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.exceptions.WriterException;
import it.sevenbits.javaformatter.interfaces.IReader;
import it.sevenbits.javaformatter.interfaces.IWriter;
import it.sevenbits.javaformatter.lexer.ILexer;
import it.sevenbits.javaformatter.lexer.ILexerFactory;
import it.sevenbits.javaformatter.lexer.IToken;
import it.sevenbits.javaformatter.lexer.LexerFactory;

/**
 * Class with functions for modifying code to correct one
 */
public class Formatter implements IFormatter {
    private int level;
    private boolean isOneWhiteSpace;
    private boolean isNewLine;
    private final ILexerFactory lexerFactory;


    /**
     * Formatter's constructor with no parameters
     */
    public Formatter() {
        level = 0;
        isOneWhiteSpace = true;
        isNewLine = false;
        this.lexerFactory = new LexerFactory();
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws WriterException, ReaderException, LexerException {
        ILexer lexer = lexerFactory.createLexer(reader);
        while (lexer.hasMoreTokens()) {
            IToken token = lexer.readToken();
            switch (token.getName()) {
                case "CURLY_BRACE_OPEN":
                    if (isNewLine) {
                        addTabulation(writer);
                    }
                    level++;
                    write(writer, token.getLexeme());
                    writer.write('\n');
                    isNewLine = true;
                    break;
                case "CURLY_BRACE_CLOSE":
                    level--;
                    addTabulation(writer);
                    write(writer, token.getLexeme());
                    if (reader.hasNext()) {
                        writer.write('\n');
                    }
                    break;
                case "WHITESPACE":
                    if (isOneWhiteSpace) {
                        write(writer, token.getLexeme());
                        isOneWhiteSpace = false;
                    }
                    break;
                case "SEMICOLON":
                    write(writer, token.getLexeme());
                    writer.write('\n');
                    isNewLine = true;
                    isOneWhiteSpace = false;
                    break;
                default:
                    if (isNewLine) {
                        addTabulation(writer);
                        isNewLine = false;
                    }
                    isOneWhiteSpace = true;
                    write(writer, token.getLexeme());
                    break;
            }
        }
    }


    private void addTabulation(final IWriter writer) throws WriterException {
        for (int i = 0; i < level; i++) {
            writer.write('\t');
        }
    }

    private void write(final IWriter writer, final String lexema) throws WriterException {
        for (char symbol : lexema.toCharArray()) {
            writer.write(symbol);
        }
    }
}

//
//while (reader.hasNext()) {
//        char symbol = reader.read();
//        switch (symbol) {
//        case '{':
//        if (isNewLine) {
//        addTabulation(writer);
//        }
//        level++;
//        writer.write(symbol);
//        writer.write('\n');
//        isNewLine = true;
//        break;
//        case '}':
//        level--;
//        addTabulation(writer);
//        writer.write(symbol);
//        if (reader.hasNext()) {
//        writer.write('\n');
//        }
//        break;
//        case ';':
//        writer.write(symbol);
//        writer.write('\n');
//        isNewLine = true;
//        isOneWhiteSpace = false;
//        break;
//        case ' ':
//        if (isOneWhiteSpace) {
//        writer.write(symbol);
//        isOneWhiteSpace = false;
//        }
//        break;
//        case '\n':
//        break;
//        case '\t':
//        break;
//default:
//        if (isNewLine) {
//        addTabulation(writer);
//        isNewLine = false;
//        }
//        isOneWhiteSpace = true;
//        writer.write(symbol);
//        break;
//        }
//        }