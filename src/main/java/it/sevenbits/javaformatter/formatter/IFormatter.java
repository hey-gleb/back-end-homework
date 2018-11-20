package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.exceptions.WriterException;
import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;
import it.sevenbits.javaformatter.interfaces.IWriter;

/**
 * IFormatter interface with functions for formatting source Java code
 */
public interface IFormatter {

    /**
     * Function's contract for formatting source Java code
     *
     * @param reader is needed for reading source code from somewhere (depends on implementation)
     * @param writer is needed for writing formatted code to somewhere (depends on implementation)
     * @throws WriterException is thrown, if something goes wrong with writing
     * @throws ReaderException is thrown, if something goes wrong with reading
     */
    void format(IReader reader, IWriter writer) throws WriterException, ReaderException;
}
