package it.sevenbits.javaformatter.formatter.stateMachine.commands;

import it.sevenbits.javaformatter.formatter.stateMachine.FormatterContext;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.properties.ConfigException;

/**
 * Implementation of ICommand interface (class command for formatter state machine)
 */
public class AppendBraceOpen implements ICommand, IWritable {
    private ITabulator tabulator;
    private FormatterContext formatterContext;

    /**
     * AppendBraceOpen constructor with one parameter
     *
     * @param formatterContext - formatter context for modifying
     */
    public AppendBraceOpen(final FormatterContext formatterContext) {
        this.tabulator = new Tabulator();
        this.formatterContext = formatterContext;
    }

    @Override
    public void execute() throws CommandException {
        try {
            formatterContext.getLevel().levelIncrement();
            write(formatterContext.getWriter(), formatterContext.getToken());
            tabulator.addTabulation(formatterContext.getWriter(), formatterContext.getLevel().getLevel());
        } catch (WriterException e) {
            throw new CommandException("Something goes wrong while executing command", e);
        } catch (ConfigException e) {
            throw new CommandException("Something goes wrong while reading config file", e);
        }
    }

    @Override
    public void write(final IWriter writer, final IToken token) throws WriterException {
        for (char symbol : token.getLexeme().toCharArray()) {
            writer.write(symbol);
        }
        writer.write('\n');
    }
}
