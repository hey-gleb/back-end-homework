package it.sevenbits.interfaces;

import it.sevenbits.exceptions.WriterException;

/**
 * Interface with function for writing symbol to somewhere
 */
public interface IWriter {

    /**
     * Writes symbol to something
     *
     * @param symbol needed to be written
     * @throws WriterException is thrown if something wrong with writing result
     */
    void write(char symbol) throws WriterException;
}
