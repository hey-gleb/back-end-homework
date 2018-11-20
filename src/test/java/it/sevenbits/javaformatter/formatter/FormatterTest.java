package it.sevenbits.javaformatter.formatter;

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
    public void SetUp() {
        formatter = new Formatter();
        stringWriter = new StringWriter();
    }

    @Test
    public void formatTest1() throws WriterException, ReaderException {
        String correctResult = "aaa {\n\tbbbb;\n\twwwwww;\n\tccc;\n}";
        stringReader = new StringReader("aaa    {bbbb;   wwwwww;    ccc;}");
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }

    @Test
    public void formatTest2() throws WriterException, ReaderException {
        String correctResult = "{\n\t{\n\t\t{\n\t\t\t{\n\t\t\t}\n\t\t}\n\t}\n}";
        stringReader = new StringReader("{{{{}}}}");
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }

    @Test
    public void formatTest3() throws WriterException, ReaderException {
        String correctResult = "public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World\");\n\t}\n}";
        stringReader = new StringReader("public class HelloWorld {     public static void main(String[] args) { System.out.println(\"Hello, World\");     } }");
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }

    @Test
    public void correctTextTest() throws WriterException, ReaderException {
        String correctResult = "aaa {\n\tbbbb;\n\tccc;\n}";
        stringReader = new StringReader("aaa {\n\tbbbb;\n\tccc;\n}");
        formatter.format(stringReader, stringWriter);
        assertEquals("Wrong result", correctResult, stringWriter.getString());
    }

    @Test(expected = ReaderException.class)
    public void textIsNullTest() throws ReaderException {
        stringReader = new StringReader(null);
    }

    @Test(expected = WriterException.class)
    public void wrongCharTest() throws WriterException {
        stringWriter.write((char) -10);
    }
}
