package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.formatter.stateMachine.FormatterContext;
import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.formatter.stateMachine.CommandTransition;
import it.sevenbits.javaformatter.formatter.stateMachine.FormatterStateTransition;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.lexer.ILexer;
import it.sevenbits.javaformatter.lexer.factory.ILexerFactory;
import it.sevenbits.javaformatter.lexer.token.IToken;
import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.lexer.factory.LexerFactory;
import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.formatter.stateMachine.commands.CommandException;

/**
 * Implementation of IFormatter interface
 */
public class FormatterStateMachine implements IFormatter {
    private final ILexerFactory lexerFactory;
    private final FormatterStateTransition formatterStateTransition;
    private final CommandTransition commandTransition;
    private final IndentLevel level;
    private final FormatterContext formatterContext;

    /**
     * Formatter's constructor with no parameters
     * @throws FormatterException is thrown if something goes wrong with formatter
     */
    public FormatterStateMachine() throws FormatterException {
        try {
            lexerFactory = new LexerFactory();
            formatterStateTransition = new FormatterStateTransition();
            level = new IndentLevel();
            formatterContext = new FormatterContext(level);
            commandTransition = new CommandTransition(formatterContext);
        } catch (ConfigException e) {
            throw new FormatterException("Something goes wrong with reading config file", e);
        }
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        IState currentState = formatterStateTransition.getDefaultState();
        ICommand command;
        formatterContext.setWriter(writer);
        try {
            Config config = Config.getInstance();
            ILexer lexer = lexerFactory.createLexer(reader, config.getProperty("LEXER_TYPE"));
            while (lexer.hasMoreTokens()) {
                IToken token = lexer.readToken();
                formatterContext.setToken(token);
                command = commandTransition.getNextCommand(currentState, token);
                command.execute();
                currentState = formatterStateTransition.getNextState(currentState, token);
            }
        } catch (CommandException e) {
            throw new FormatterException("Something goes wrong while executing state machine command", e);
        } catch (LexerException e) {
            throw new FormatterException("Something goes wrong with Lexer", e);
        } catch (ConfigException e) {
            throw new FormatterException("Something goes wrong while reading config file", e);
        }
    }
}
