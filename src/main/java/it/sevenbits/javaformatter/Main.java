package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.formatter.FormatterException;
import it.sevenbits.javaformatter.formatter.FormatterStateMachine;
import it.sevenbits.javaformatter.formatter.IFormatter;
import it.sevenbits.javaformatter.io.input.FileReader;
import it.sevenbits.javaformatter.io.output.FileWriter;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.properties.ConfigException;


/**
 * Main application entry point
 */
public final class Main {

    private Main() {
    }

    /**
     * Main function for application
     *
     * @param args - console argument
     * @throws ReaderException    is thrown if something goes wrong while reading
     * @throws FormatterException is thrown if something goes wrong with formatter work
     * @throws ConfigException    is thrown if something goes wrong with reading config file
     * @throws WriterException    is thrown if something goes wrong with writing symbols
     */
    public static void main(final String[] args) throws ReaderException, FormatterException, ConfigException, WriterException {
        try (FileReader fileReader = new FileReader(args[0]);
             FileWriter fileWriter = new FileWriter(args[1])) {
            IFormatter formatter = new FormatterStateMachine();
            formatter.format(fileReader, fileWriter);
        }
        System.out.println("Formatter is done");
    }
}