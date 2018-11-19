package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.exceptions.ReaderException;
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
