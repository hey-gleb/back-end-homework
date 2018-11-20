package it.sevenbits.javaformatter.io.output;

import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.io.output.StringWriter;
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
    public void testWriteChar() throws WriterException {
        String testString = "Hello";
        for (char symbol : testString.toCharArray())
            stringWriter.write(symbol);
    }


    @Test(expected = WriterException.class)
    public void testWriteWrongChar() throws WriterException {
        stringWriter.write((char) -10);
    }

}
