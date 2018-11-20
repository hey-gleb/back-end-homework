package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.interfaces.IReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LexerTest {
    private IReader reader;
    private ILexer lexer;

    @Before
    public void setUp() throws ReaderException {
        reader = mock(IReader.class);
        lexer = new Lexer(reader);
    }

    @Test
    public void testHasMoreTokens() throws ReaderException {
        when(reader.hasNext()).thenReturn(true, false);
        when(reader.read()).thenReturn('{');
        assertFalse("Wrong result", lexer.hasMoreTokens());
    }

    @Test
    public void testReadToken() {

    }
}
