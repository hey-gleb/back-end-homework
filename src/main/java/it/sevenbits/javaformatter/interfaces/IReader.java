package it.sevenbits.javaformatter.interfaces;

import it.sevenbits.javaformatter.exceptions.ReaderException;

/**
 * Interface with functions for reading one symbol from somewhere and checking is there next symbol or not
 */
public interface IReader {
    /**
     * Checks is there next symbol or not
     *
     * @return true if there is next symbol, false is not
     * @throws ReaderException is thrown if something wrong with checking next symbol
     */
    boolean hasNext() throws ReaderException;

    /**
     * Reads next symbol
     *
     * @return next symbol
     * @throws ReaderException is thrown, if something goes wrong with reading
     */
    char read() throws ReaderException;
}
