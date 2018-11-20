package it.sevenbits.javaformatter.stringIO;

import it.sevenbits.javaformatter.exceptions.ReaderException;
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
    public void textIsNullTest() throws ReaderException {
        stringReader = new StringReader(null);
    }

    @Test
    public void hasNextTest() throws ReaderException {
        stringReader = new StringReader("Test");
        assertTrue("Wrong result!", stringReader.hasNext());
    }

    @Test
    public void readTest() throws ReaderException {
        stringReader = new StringReader("Test line");
        assertEquals("Wrong result!", 'T', stringReader.read());
    }

    @Test (expected = ReaderException.class)
    public void nullStringTest() throws ReaderException {
        stringReader = new StringReader(null);
    }
}
