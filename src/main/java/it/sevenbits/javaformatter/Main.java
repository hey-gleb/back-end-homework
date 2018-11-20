package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.io.input.FileReader;
import it.sevenbits.javaformatter.formatter.Formatter;
import it.sevenbits.javaformatter.io.output.StringWriter;

/**
 * Main application entry point
 */
public final class Main {

    private Main() {
    }

    /**
     * Main function for application
     *
     * @param args - console arguments
     * @throws WriterException is thrown if something wrong with writing result
     * @throws ReaderException is thrown if something wrong with reading plain text
     * @throws LexerException is thrown if something wrong with lexeme
     */
    public static void main(final String[] args) throws WriterException, ReaderException, LexerException {
        StringWriter stringWriter = new StringWriter();
        try (FileReader fileReader = new FileReader("readFile.txt")) {
            Formatter formatter = new Formatter();

            formatter.format(fileReader, stringWriter);
            System.out.println(stringWriter.getString());
        }
    }
}
