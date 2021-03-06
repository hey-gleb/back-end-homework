package it.sevenbits.javaformatter.formatter.stateMachine.commands;

import it.sevenbits.javaformatter.formatter.stateMachine.FormatterContext;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.lexer.token.Token;

public class AppendAfterNewLine implements ICommand, IWritable {
    private FormatterContext formatterContext;

    /**
     * AppendAfterNewLineTabulation constructor with one parameter
     *
     * @param formatterContext - formatter context for modifying
     */
    public AppendAfterNewLine(final FormatterContext formatterContext) {
        this.formatterContext = formatterContext;
    }

    @Override
    public void execute() throws CommandException {
        try {
            write(formatterContext.getWriter(), new Token("\n"));
            write(formatterContext.getWriter(), formatterContext.getToken());
        } catch (WriterException e) {
            throw new CommandException("Something goes wrong while executing command", e);
        }
    }

    @Override
    public void write(final IWriter writer, final IToken token) throws WriterException {
        for (char symbol : token.getLexeme().toCharArray()) {
            writer.write(symbol);
        }
    }
}
