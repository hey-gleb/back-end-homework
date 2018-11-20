package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.exceptions.LexerException;
import it.sevenbits.javaformatter.exceptions.ReaderException;
import it.sevenbits.javaformatter.exceptions.WriterException;
import it.sevenbits.javaformatter.stringIO.StringReader;
import it.sevenbits.javaformatter.stringIO.StringWriter;
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
        stringReader = new StringReader("public class HelloWorld {     public static void main(String[] args) { System.out.println(\"Hello, World\");     } }");
    }

    @Test
    public void formatTest() throws WriterException, ReaderException, LexerException {
        String correctResult = "public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World\");\n\t}\n}";
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }


}
