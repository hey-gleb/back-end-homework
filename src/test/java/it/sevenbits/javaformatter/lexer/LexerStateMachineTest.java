package it.sevenbits.javaformatter.lexer;

import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LexerStateMachineTest {
    private IReader reader;
    private ILexer lexer;

    @Test
    public void testBraceWithText() throws LexerException {
        try {
            reader = new StringReader("{text");
            lexer = new LexerStateMachine(reader);
            assertEquals("{", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testBraceOnly() throws LexerException {
        try {
            reader = new StringReader("{");
            lexer = new LexerStateMachine(reader);
            assertEquals("{", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testText() throws LexerException {
        try {
            reader = new StringReader("abc");
            lexer = new LexerStateMachine(reader);
            assertEquals("abc", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testSingleLineComment() throws LexerException {
        try {
            reader = new StringReader("//Hello {;}");
            lexer = new LexerStateMachine(reader);
            assertEquals("//", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testWhitespaceOnly() throws LexerException {
        try {
            reader = new StringReader("                ");
            lexer = new LexerStateMachine(reader);
            assertEquals(" ", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testMultiCommentInSingleLine() throws LexerException {
        try {
            reader = new StringReader("/* test comment */");
            lexer = new LexerStateMachine(reader);
            assertEquals("/*", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("test", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("comment", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("*/", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testMultiCommentInMultiLines() throws LexerException {
        try {
            reader = new StringReader("/* test \n comment \n 2 */");
            lexer = new LexerStateMachine(reader);
            assertEquals("/*", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testEmptyInput() throws LexerException {
        try {
            reader = new StringReader("");
            lexer = new LexerStateMachine(reader);
            assertEquals("", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }


    @Test
    public void testCharWithBrace() throws LexerException {
        try {
            reader = new StringReader("A{");
            lexer = new LexerStateMachine(reader);
            assertEquals("A", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testMultiCommentInSingleLineComment() throws LexerException {
        try {
            reader = new StringReader("//  /* test comment */");
            lexer = new LexerStateMachine(reader);
            assertEquals("//", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }

    @Test
    public void testTextAfterMultiComment() throws LexerException {
        try {
            reader = new StringReader("/* test comment */ Hello");
            lexer = new LexerStateMachine(reader);
            assertEquals("/*", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("test", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("comment", lexer.readToken().getLexeme());
            assertEquals(" ", lexer.readToken().getLexeme());
            assertEquals("*/", lexer.readToken().getLexeme());
        } catch (ReaderException e) {
            throw new LexerException("Something goes wrong with reading", e);
        }
    }
}
