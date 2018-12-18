package it.sevenbits.javaformatter.formatter.stateMachine;


import it.sevenbits.javaformatter.formatter.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.lexer.token.IToken;

/**
 *
 */
public class CommandTransition {
    private final CommandMap commandMap;

    /**
     * CommandTransition constructor with no parameters
     *
     * @param formatterContext - formatter context for modifying
     * @throws ConfigException is thrown if something goes wrong while reading config file
     */
    public CommandTransition(final FormatterContext formatterContext) throws ConfigException {
        commandMap = new CommandMap(formatterContext);
    }

    /**
     * Function gets and return next command
     *
     * @param currentState - current formatter state
     * @param currentToken - current token
     * @return next command
     */
    public ICommand getNextCommand(final IState currentState, final IToken currentToken) {
        return commandMap.getCommand(currentState, currentToken);
    }
}
