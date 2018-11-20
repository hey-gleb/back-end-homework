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
    public void setUp() {
        reader = mock(IReader.class);
        lexer = new Lexer(reader);
    }

    @Test
    public void testHasMoreTokens() throws LexerException {
        try {
            when(reader.hasNext()).thenReturn(true, false);
            assertTrue("Wrong result", lexer.hasMoreTokens());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testReadToken() throws LexerException {
        try {
            when(reader.hasNext()).thenReturn(true, true, false);
            when(reader.read()).thenReturn('{');
            assertEquals("Wrong result!", new Token("CURLY_BRACE_OPEN", "{").getName(), lexer.readToken().getName());
            assertEquals("Wrong result!", new Token("CURLY_BRACE_OPEN", "{").getLexeme(), lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }
}
