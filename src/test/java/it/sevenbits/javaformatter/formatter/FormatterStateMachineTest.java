package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.StringReader;
import it.sevenbits.javaformatter.io.output.StringWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.properties.ConfigException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FormatterStateMachineTest {
    private IFormatter formatter;
    private IReader reader;
    private StringWriter writer;

    @Before
    public void setUp() throws FormatterException {
        formatter = new FormatterStateMachine();
    }

    @Test
    public void testBraces() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("{{{}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n" +
                "    {\n" +
                "        {\n" +
                "            \n" +
                "        }\n" +
                "    }\n" +
                "}", writer.getString());
    }

    @Test
    public void testOnlyWhitespaces() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("                  ");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("", writer.getString());
    }

    @Test
    public void testOnlyStringLiteral() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("\"{{Hello}}   ;{}; // \"");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("\"{{Hello}}   ;{}; // \"", writer.getString());
    }

    @Test
    public void testTextInBraces() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("{Hello;}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("{\n    Hello;\n}", writer.getString());
    }

    @Test
    public void testOnlySingleLineComment() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("// test comment; {}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("// test comment; {}", writer.getString());
    }

    @Test
    public void testOnlyMultiLineComment() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("/* test comment; {abc} \n \n ;;; */");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("/* test comment; {abc} \n \n ;;; */", writer.getString());
    }

    @Test
    public void testMultiLineCommentInSingleComment() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("// ;; {abc} test comment /* {test multi line comment} */");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("// ;; {abc} test comment /* {test multi line comment} */", writer.getString());
    }

    @Test
    public void testSingleLineCommentInMultiComment() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("/* {test; multi; // line; comment;} */");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("/* {test; multi; // line; comment;} */", writer.getString());
    }

    @Test
    public void testMultiSpaces() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("S    p        a      c           e");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("S p a c e", writer.getString());
    }

    @Test(expected = FormatterException.class)
    public void testWriterMockException() throws ReaderException, FormatterException, ConfigException, WriterException {
        reader = new StringReader("test");
        writer = mock(StringWriter.class);
        doThrow(new WriterException("Oops writer doesn't work")).when(writer).write(any(char.class));
        formatter.format(reader, writer);
    }

    @Test
    public void testJavaDoc() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("/**\n* Line 1\n* Line 2\n* Line 3\n*/");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("/**\n* Line 1\n* Line 2\n* Line 3\n*/", writer.getString());
    }

    @Test
    public void testFormatMultiLine() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("aaa { bbb; ccc; ddd; }");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("aaa {\n    bbb;\n    ccc;\n    ddd;\n}", writer.getString());
    }

    @Test
    public void testFormatOnlySemicolonLines() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("aaa; \n bbb; ccc;");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("aaa;\nbbb;\nccc;", writer.getString());
    }

    @Test
    public void testFormatMixedLines() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("asdasdasdasd\n" +
                "asdasdasd\n" +
                "asdasd;\n" +
                "       asdasdas;\n" +
                "       asdasd\n" +
                "       aasd");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("asdasdasdasd\nasdasdasd\nasdasd;\nasdasdas;\nasdasd\naasd", writer.getString());
    }

    @Test
    public void testFormatRealCode() throws ReaderException, FormatterException, ConfigException {
        reader = new StringReader("public final class Main {\n" +
                "    private Main() {}\n" +
                "    /*\n" +
                "        asdasdasdasd\n" +
                "        asdasd;\n" +
                "        asdasdas;\n" +
                "        aasd\n" +
                "     */\n" +
                "    public static void main(final String[] args) throws ReaderException, FormatterException, ConfigException, WriterException {\n" +
                "        FileReader fileReader = new FileReader(args[0]);\n" +
                "        FileWriter fileWriter = new FileWriter(args[1]);\n" +
                "                        IFormatter formatter = new FormatterStateMachine();\n" +
                "\n" +
                "\n" +
                "            formatter.format(fileReader, fileWriter);\n" +
                "            //HERE\n" +
                "        System.out.println(\"Formatter is done\"); //HERE 2\n" +
                "    }\n" +
                "}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        assertEquals("public final class Main {\n" +
                "    private Main() {}\n" +
                "    /*\n" +
                "        asdasdasdasd\n" +
                "        asdasd;\n" +
                "        asdasdas;\n" +
                "        aasd\n" +
                "     */\n" +
                "    public static void main(final String[] args) throws ReaderException, FormatterException, ConfigException, WriterException {\n" +
                "        FileReader fileReader = new FileReader(args[0]);\n" +
                "        FileWriter fileWriter = new FileWriter(args[1]);\n" +
                "                        IFormatter formatter = new FormatterStateMachine();\n" +
                "            formatter.format(fileReader, fileWriter);\n" +
                "            //HERE\n" +
                "        System.out.println(\"Formatter is done\"); //HERE 2\n" +
                "    }\n" +
                "}", writer.getString());
    }
}
