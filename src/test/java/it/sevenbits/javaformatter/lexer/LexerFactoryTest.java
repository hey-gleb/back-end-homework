package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.fileIO.FileReader;
import it.sevenbits.javaformatter.interfaces.IReader;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LexerFactoryTest {
    private IReader reader;
    private ILexerFactory lexerFactory;

    @Before
    public void setUp() {
        reader = mock(IReader.class);
        lexerFactory = mock(ILexerFactory.class);
    }

    @Test
    public void testCreateLexer() throws ReaderException {
        try {
            ILexer lexer = lexerFactory.createLexer(reader);
        } catch (Exception e) {
            throw new ReaderException("Something goes wrong", e);
        }
    }

    @Test(expected = ReaderException.class)
    public void testCreateLexerException() throws ReaderException {
        try {
            IReader reader = new FileReader("");
            ILexer lexer = lexerFactory.createLexer(reader);
        } catch (Exception e) {
            throw new ReaderException("Something goes wrong", e);
        }
    }
}
