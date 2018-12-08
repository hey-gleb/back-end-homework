package it.sevenbits.javaformatter.io.output;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringWriterTest {
    private StringWriter stringWriter;

    @Before
    public void setUp() {
        stringWriter = new StringWriter();
    }

    @Test
    public void testWrite() throws WriterException {
        stringWriter.write('t');
        stringWriter.write('e');
        stringWriter.write('s');
        stringWriter.write('t');
        assertEquals("test", stringWriter.getString());
    }

    @Test
    public void testToString() throws WriterException {
        stringWriter.write('{');
        assertEquals("{", stringWriter.getString());
    }

}
