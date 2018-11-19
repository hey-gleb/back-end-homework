package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;

import java.io.*;

public class FileReader implements IReader {
    private BufferedReader bufferedReader;
    private int readChar;

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
    public boolean hasNext() throws ReaderException {
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
}
