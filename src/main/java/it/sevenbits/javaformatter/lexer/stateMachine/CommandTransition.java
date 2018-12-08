package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.lexer.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;

/**
 * Class translator for commands (part between Lexer and commandMap)
 */
public class CommandTransition {
    private final CommandMap commandMap;

    /**
     * CommandTransition constructor with no parameters
     *
     * @param lexerContext - lexer context for commands
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public CommandTransition(final LexerContext lexerContext) throws ConfigException {
        commandMap = new CommandMap(lexerContext);
    }

    /**
     * Function gets next command and return it
     *
     * @param currentState  - current lexer state
     * @param currentSymbol - current symbol (signal)
     * @return next command
     */
    public ICommand getNextCommand(final IState currentState, final char currentSymbol) {
        return commandMap.getCommand(currentState, currentSymbol);
    }
}
