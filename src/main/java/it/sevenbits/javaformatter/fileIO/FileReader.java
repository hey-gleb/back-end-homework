package it.sevenbits.javaformatter.fileIO;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of IReader interface
 */
public class FileReader implements IReader, AutoCloseable {
    private BufferedReader bufferedReader;
    private int readChar;

    /**
     * FileReader constructor with one parameter
     *
     * @param filePath need to open thread
     * @throws ReaderException is thrown, if file doesn't exist or something goes wrong with reading file
     */
    public FileReader(final String filePath) throws ReaderException {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath))); /** Added charset name **/
            readChar = bufferedReader.read();
        } catch (FileNotFoundException e) {
            throw new ReaderException("File does not exist", e);
        } catch (IOException e) {
            throw new ReaderException("Something goes wrong while read file", e);
        }
    }

    @Override
    public boolean hasNext() {
        return readChar != -1;
    }

    @Override
    public char read() throws ReaderException {
        try {
            int currentChar = readChar;
            readChar = bufferedReader.read();
            return (char) currentChar;
        } catch (IOException e) {
            throw new ReaderException("Something goes wrong while read file", e);
        }
    }

    @Override
    public void close() throws ReaderException {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new ReaderException("Something goes wrong with closing thread", e);
        }
    }
}
