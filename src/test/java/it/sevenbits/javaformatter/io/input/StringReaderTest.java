package it.sevenbits.javaformatter.io.input;

import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.StringReader;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class StringReaderTest {
    private StringReader stringReader;

    @Before
    public void setUp() {
    }

    @Test(expected = ReaderException.class)
    public void testTextIsNull() throws ReaderException {
        stringReader = new StringReader(null);
    }

    @Test
    public void testHasNextSymbol() throws ReaderException {
        stringReader = new StringReader("Test");
        assertTrue("Wrong result!", stringReader.hasNext());
    }

    @Test
    public void testReadSymbol() throws ReaderException {
        stringReader = new StringReader("Test line");
        assertEquals("Wrong result!", 'T', stringReader.read());
    }

    @Test (expected = ReaderException.class)
    public void testReadFromNullString() throws ReaderException {
        stringReader = new StringReader(null);
    }
}
