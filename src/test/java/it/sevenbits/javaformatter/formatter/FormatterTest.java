package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.io.input.StringReader;
import it.sevenbits.javaformatter.io.output.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTest {
    private Formatter formatter;
    private StringReader stringReader;
    private StringWriter stringWriter;

    @Before
    public void SetUp() throws ReaderException {
        formatter = new Formatter();
        stringWriter = new StringWriter();
        stringReader = new StringReader("aaa   {   bbbb;   ccc;   wwww;  }");
    }

    @Test
    public void testFormatCode() throws WriterException, ReaderException, LexerException {
        String correctResult = "aaa {\n    bbbb;\n    ccc;\n    wwww;\n}";
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }


}
