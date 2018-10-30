package it.sevenbits;

import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.formatter.Formatter;
import it.sevenbits.stringIO.StringReader;
import it.sevenbits.stringIO.StringWriter;

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
     * @throws WriterException thrown if something wrong with writing result
     * @throws ReaderException thrown if something wrong with reading plain text
     */
    public static void main(final String[] args) throws WriterException, ReaderException {
        StringReader stringReader = new StringReader("aaa { bbbb;      ccc;   }");
        StringWriter stringWriter = new StringWriter();

        Formatter formatter = new Formatter();

        formatter.format(stringReader, stringWriter);
        System.out.println(stringWriter.getString());
    }
}
