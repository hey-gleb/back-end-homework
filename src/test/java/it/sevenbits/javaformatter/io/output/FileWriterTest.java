package it.sevenbits.javaformatter.io.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class FileWriterTest {
    private FileWriter fileWriter;

    @Before
    public void setUp() throws WriterException {
        fileWriter = new FileWriter(Objects.requireNonNull(getClass().getClassLoader().getResource("testOutput")).getPath());
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
        FileWriter fw = new FileWriter(Objects.requireNonNull(getClass().getClassLoader().getResource("testOutput")).getPath());
        fw.close();
        fw.write('a');
    }
}
