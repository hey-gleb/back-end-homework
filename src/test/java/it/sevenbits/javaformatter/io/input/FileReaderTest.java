package it.sevenbits.javaformatter.io.input;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private FileReader fileReader;

    @Before
    public void setUp() throws ReaderException {
        fileReader = new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("testInput")).getPath());
    }

    @After
    public void close() throws ReaderException {
        fileReader.close();
    }

    @Test
    public void testHasNextSymbol() {
        assertTrue("Wrong result!", fileReader.hasNext());
    }

    @Test
    public void testReadSymbol() throws ReaderException {
        assertEquals('t', fileReader.read());
        assertEquals('e', fileReader.read());
        assertEquals('s', fileReader.read());
        assertEquals('t', fileReader.read());
    }

    @Test(expected = ReaderException.class)
    public void testReadFromCloseStream() throws ReaderException {
        close();
        fileReader.read();
    }

    @Test(expected = ReaderException.class)
    public void testPathIsNotExist() throws ReaderException {
        fileReader = new FileReader("lol");
    }

}
