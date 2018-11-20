package it.sevenbits.javaformatter.stringIO;

import it.sevenbits.javaformatter.exceptions.WriterException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class StringWriterTest {
    private StringWriter stringWriter;

    @Before
    public void setUp() {
        stringWriter = new StringWriter();
    }

    @Test
    public void writeCharTest() throws WriterException {
        String testString = "Hello";
        for (char symbol : testString.toCharArray())
            stringWriter.write(symbol);
    }

    @Test(expected = WriterException.class)
    public void writeWrongCharTest() throws WriterException {
        stringWriter.write((char) -10);
    }

}
