package it.sevenbits.javaformatter.io.output;

import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.io.output.FileWriter;
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
    public void testWriteCharToFile() throws WriterException {
        fileWriter.write('1');
    }

    @Test(expected = WriterException.class)
    public void testWriteToCloseStream() throws WriterException {
        close();
        fileWriter.write('a');
    }
}
