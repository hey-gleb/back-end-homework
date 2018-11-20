package it.sevenbits.javaformatter.io.input;

import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.FileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private FileReader fileReader;

    @Before
    public void setUp() throws ReaderException {
        fileReader = new FileReader("readFile.txt");
    }

    @After
    public void close() throws ReaderException {
        fileReader.close();
    }

    @Test
    public void hasNextTest() {
        assertTrue("Wrong result!", fileReader.hasNext());
    }

    @Test
    public void readTest() throws ReaderException {
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader("readFile.txt"))) {
            assertEquals("Wrong result!", bufferedReader.readLine().charAt(0), fileReader.read());
        } catch (FileNotFoundException e) {
            throw new ReaderException("File not found", e);
        } catch (IOException e) {
            throw new ReaderException("Something goes wrong with reading file", e);
        }
    }
}
