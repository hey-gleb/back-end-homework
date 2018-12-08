package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.properties.ConfigException;

/**
 * IFormatter interface with functions for formatting source Java code
 */
public interface IFormatter {

    /**
     * Function's contract for formatting source Java code
     *
     * @param reader is needed for reading source code from somewhere (depends on implementation)
     * @param writer is needed for writing formatted code to somewhere (depends on implementation)
     * @throws FormatterException is thrown, if something goes wrong with formatter
     * @throws ConfigException    is thrown if something goes wrong with reading config file
     */
    void format(IReader reader, IWriter writer) throws FormatterException, ConfigException;
}
