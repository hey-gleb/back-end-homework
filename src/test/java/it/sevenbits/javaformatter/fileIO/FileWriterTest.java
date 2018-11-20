package it.sevenbits.javaformatter.fileIO;

import it.sevenbits.javaformatter.exceptions.WriterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileWriterTest {
    private FileWriter fileWriter;

    @Before
    public void setUp() throws WriterException {
        fileWriter = new FileWriter("writeFile.txt");
    }

    @After
    public void close() throws WriterException {
        fileWriter.close();
    }

    @Test
    public void writeCharToFileTest() throws WriterException {
        fileWriter.write('1');
    }
}
