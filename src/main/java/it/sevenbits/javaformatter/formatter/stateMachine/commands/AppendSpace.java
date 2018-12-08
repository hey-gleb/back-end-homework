package it.sevenbits.javaformatter.formatter.stateMachine.commands;


import it.sevenbits.javaformatter.formatter.stateMachine.FormatterContext;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.lexer.token.IToken;

/**
 * Implementation of ICommand interface (class command for formatter state machine)
 */
public class AppendSpace implements ICommand, IWritable {
    private FormatterContext formatterContext;

    /**
     * AppendSpcae constructor with one parameter
     *
     * @param formatterContext - formatter context for modifying
     */
    public AppendSpace(final FormatterContext formatterContext) {
        this.formatterContext = formatterContext;
    }

    @Override
    public void execute() throws CommandException {
        try {
            write(formatterContext.getWriter(), formatterContext.getToken());
        } catch (WriterException e) {
            throw new CommandException("Something goes wrong while executing command", e);
        }
    }

    @Override
    public void write(final IWriter writer, final IToken token) throws WriterException {
        writer.write(' ');
    }
}
