package it.sevenbits.javaformatter.io.output;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of IWriter interface
 */
public class FileWriter implements IWriter, AutoCloseable {
    private BufferedWriter bufferedWriter;

    /**
     * FileWriter constructor with one parameter
     *
     * @param filePath need to open thread
     * @throws WriterException is thrown if file doesn't exist
     */
    public FileWriter(final String filePath) throws WriterException {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new WriterException("File does not exist", e);
        }
    }

    @Override
    public void write(final char symbol) throws WriterException {
        try {
            bufferedWriter.append(symbol);
        } catch (IOException e) {
            throw new WriterException("Something goes wrong while writing symbol into the file", e);
        }
    }

    @Override
    public void close() throws WriterException {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new WriterException("Something goes wrong with closing thread", e);
        }
    }
}
