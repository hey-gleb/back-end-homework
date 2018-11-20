package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.IReader;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
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
        assertTrue("Wrong result", lexer.hasMoreTokens());
    }

    @Test
    public void testReadToken() throws ReaderException, LexerException {
        when(reader.hasNext()).thenReturn(true, false);
        when(reader.read()).thenReturn('{');
        assertEquals("Wrong result!", new Token("{"), lexer.readToken());
    }

    @Test(expected = LexerException.class)
    public void emptyLexemaTest() throws LexerException, ReaderException {
        when(reader.hasNext()).thenReturn(true, false);
        when(reader.read()).thenReturn(null);
        lexer.readToken();
    }
}
