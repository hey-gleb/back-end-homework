package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.lexer.stateMachine.commands.ICommand;
import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.Pair;
import it.sevenbits.javaformatter.lexer.stateMachine.commands.IgnoreCommand;
import it.sevenbits.javaformatter.lexer.stateMachine.commands.ReturnCommand;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.lexer.stateMachine.commands.AppendCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains lexer commands (necessary for lexer state machine)
 */
public class CommandMap {
    private Map<Pair<IState, Character>, ICommand> commandMap;

    /**
     * CommandMap constructor with no parameters
     *
     * @param lexerContext - lexer context for modifying
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    CommandMap(final LexerContext lexerContext) throws ConfigException {
        Config config = Config.getInstance();
        commandMap = new HashMap<>();
        IState defaultState = new LexerState("DEFAULT");
        IState textState = new LexerState(config.getProperty("LEXER_STATE_TEXT"));
        IState spaceState = new LexerState(config.getProperty("LEXER_STATE_SPACE"));
        IState reservedState = new LexerState(config.getProperty("LEXER_STATE_RESERVED"));
        IState stringLiteral = new LexerState(config.getProperty("LEXER_STATE_STRING_LITERAL"));
        IState probablyComment = new LexerState(config.getProperty("LEXER_STATE_MAYBE_COMMENT"));
        IState singleComment = new LexerState(config.getProperty("LEXER_STATE_SINGLELINE_COM"));
        IState multiComment = new LexerState(config.getProperty("LEXER_STATE_MULTILINE_COM"));
        IState probablyEndMultiComment = new LexerState(config.getProperty("LEXER_STATE_MAYBER_END_COM"));
        IState endMultiComment = new LexerState(config.getProperty("LEXER_STATE_END_MULTILINE_COM"));

        ICommand addCommand = new AppendCommand(lexerContext);
        ICommand returnCommand = new ReturnCommand(lexerContext);
        ICommand ignoreCommand = new IgnoreCommand(lexerContext);

        commandMap.put(new Pair<>(defaultState, null), addCommand);
        commandMap.put(new Pair<>(defaultState, '\0'), ignoreCommand);
        commandMap.put(new Pair<>(reservedState, null), returnCommand);

        commandMap.put(new Pair<>(textState, null), addCommand);
        commandMap.put(new Pair<>(textState, '}'), returnCommand);
        commandMap.put(new Pair<>(textState, '{'), returnCommand);
        commandMap.put(new Pair<>(textState, ';'), returnCommand);
        commandMap.put(new Pair<>(textState, ' '), returnCommand);
        commandMap.put(new Pair<>(textState, '"'), returnCommand);
        commandMap.put(new Pair<>(textState, '\n'), addCommand);
        commandMap.put(new Pair<>(defaultState, '/'), addCommand);

        commandMap.put(new Pair<>(stringLiteral, null), addCommand);

        commandMap.put(new Pair<>(spaceState, ' '), ignoreCommand);
        commandMap.put(new Pair<>(spaceState, null), returnCommand);
        commandMap.put(new Pair<>(spaceState, '"'), returnCommand);

        commandMap.put(new Pair<>(probablyComment, null), addCommand);
        commandMap.put(new Pair<>(singleComment, null), returnCommand);
        commandMap.put(new Pair<>(multiComment, null), returnCommand);

        commandMap.put(new Pair<>(probablyEndMultiComment, '/'), addCommand);
        commandMap.put(new Pair<>(probablyEndMultiComment, null), addCommand);
        commandMap.put(new Pair<>(endMultiComment, null), returnCommand);
    }

    /**
     * Function returns command
     *
     * @param currentState - current lexer state
     * @param symbol       - current symbol (signal)
     * @return command depends on current signal and state
     */
    public ICommand getCommand(final IState currentState, final char symbol) {
        if (commandMap.containsKey(new Pair<>(currentState, symbol))) {
            return commandMap.get(new Pair<>(currentState, symbol));
        } else {
            return commandMap.get(new Pair<>(currentState, (Character) null));
        }
    }
}
