package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.formatter.IndentLevel;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.StringWriter;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.lexer.token.Token;

/**
 * Class describes formatter context for commands
 */
public class FormatterContext {
    private IWriter writer;
    private IToken token;
    private IndentLevel level;

    /**
     * FormatterContext constructor with one parameter
     * @param level - class contains current indent level
     */
    public FormatterContext(final IndentLevel level) {
        this.writer = new StringWriter();
        this.token = new Token();
        this.level = level;
    }

    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    public void setToken(final IToken token) {
        this.token = token;
    }

    public IWriter getWriter() {

        return writer;
    }

    public IToken getToken() {
        return token;
    }

    public IndentLevel getLevel() {
        return level;
    }

//TODO: fix this class
}
