package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.lexer.ILexer;
import it.sevenbits.javaformatter.lexer.factory.ILexerFactory;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.lexer.factory.LexerFactory;
import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;

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
        lexerFactory = new LexerFactory();
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        try {
            Config config = Config.getInstance();
            ILexer lexer = lexerFactory.createLexer(reader, config.getProperty("LEXER_TYPE"));
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
                            isOneWhiteSpace = false;
                        }
                        isOneWhiteSpace = true;
                        write(writer, token.getLexeme());
                        break;
                }
            }
        } catch (WriterException e) {
            throw new FormatterException("Something wrong with writing", e);
        } catch (ReaderException e) {
            throw new FormatterException("Something wrong with reading", e);
        } catch (LexerException e) {
            throw new FormatterException("Something wrong with lexer", e);
        } catch (ConfigException e) {
            throw new FormatterException("Something wrong with config", e);
        }
    }


    private void addTabulation(final IWriter writer) throws WriterException {
        final int numberOfWhiteSpaces = 4;
        for (int i = 0; i < level * numberOfWhiteSpaces; i++) {
            writer.write(' ');
        }
    }

    private void write(final IWriter writer, final String lexema) throws WriterException {
        for (char symbol : lexema.toCharArray()) {
            writer.write(symbol);
        }
    }
}