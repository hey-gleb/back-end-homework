package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.exceptions.LexerException;
import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.exceptions.WriterException;
import it.sevenbits.javaformatter.fileIO.FileReader;
import it.sevenbits.javaformatter.formatter.Formatter;
import it.sevenbits.javaformatter.stringIO.StringWriter;

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
