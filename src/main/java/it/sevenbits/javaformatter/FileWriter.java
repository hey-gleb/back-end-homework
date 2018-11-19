package it.sevenbits.javaformatter;

import it.sevenbits.javaformatter.interfaces.IWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileWriter implements IWriter {
    private BufferedWriter bufferedWriter;

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
            throw new WriterException("Something goes wrong while writing symbol into the file");
        }
    }
}
