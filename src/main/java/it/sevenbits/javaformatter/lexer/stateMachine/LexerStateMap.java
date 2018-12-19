package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.Pair;
import it.sevenbits.javaformatter.stateMachineSupport.IState;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains lexer states
 */
public class LexerStateMap {
    private final LexerState defaultState = new LexerState("DEFAULT");

    private final Map<Pair<IState, Character>, IState> lexerStates;

    /**
     * LexerStateMap constructor with no parameters
     *
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    LexerStateMap() throws ConfigException {
        lexerStates = new HashMap<>();
        Config config = Config.getInstance();

        IState textState = new LexerState(config.getProperty("LEXER_STATE_TEXT"));
        IState spaceState = new LexerState(config.getProperty("LEXER_STATE_SPACE"));
        IState reservedState = new LexerState(config.getProperty("LEXER_STATE_RESERVED"));
        IState stringLiteral = new LexerState(config.getProperty("LEXER_STATE_STRING_LITERAL"));
        IState probablyComment = new LexerState(config.getProperty("LEXER_STATE_MAYBE_COMMENT"));
        IState singleComment = new LexerState(config.getProperty("LEXER_STATE_SINGLELINE_COM"));
        IState multiComment = new LexerState(config.getProperty("LEXER_STATE_MULTILINE_COM"));
        IState probablyEndMultiComment = new LexerState(config.getProperty("LEXER_STATE_MAYBER_END_COM"));
        IState endMultiComment = new LexerState(config.getProperty("LEXER_STATE_END_MULTILINE_COM"));

        lexerStates.put(new Pair<>(defaultState, null), textState);
        lexerStates.put(new Pair<>(defaultState, '\0'), defaultState);
        lexerStates.put(new Pair<>(defaultState, '{'), reservedState);
        lexerStates.put(new Pair<>(defaultState, '}'), reservedState);
        lexerStates.put(new Pair<>(defaultState, ';'), reservedState);
        lexerStates.put(new Pair<>(defaultState, '"'), stringLiteral);
        lexerStates.put(new Pair<>(defaultState, '/'), probablyComment);
        lexerStates.put(new Pair<>(defaultState, '*'), probablyEndMultiComment);
        lexerStates.put(new Pair<>(defaultState, '\n'), reservedState);

        lexerStates.put(new Pair<>(probablyComment, '/'), singleComment);
        lexerStates.put(new Pair<>(probablyComment, '*'), multiComment);
        lexerStates.put(new Pair<>(probablyComment, null), textState);

        lexerStates.put(new Pair<>(multiComment, '*'), probablyEndMultiComment);
        lexerStates.put(new Pair<>(multiComment, null), multiComment);

        lexerStates.put(new Pair<>(probablyEndMultiComment, null), multiComment);
        lexerStates.put(new Pair<>(probablyEndMultiComment, '/'), endMultiComment);

        lexerStates.put(new Pair<>(stringLiteral, null), stringLiteral);
        lexerStates.put(new Pair<>(stringLiteral, '"'), textState);

        lexerStates.put(new Pair<>(textState, null), textState);
        lexerStates.put(new Pair<>(textState, '{'), reservedState);
        lexerStates.put(new Pair<>(textState, '}'), reservedState);
        lexerStates.put(new Pair<>(textState, ';'), reservedState);
        lexerStates.put(new Pair<>(textState, '"'), stringLiteral);
        lexerStates.put(new Pair<>(textState, '/'), probablyComment);

        lexerStates.put(new Pair<>(textState, ' '), spaceState);
        lexerStates.put(new Pair<>(spaceState, ' '), spaceState);
        lexerStates.put(new Pair<>(defaultState, ' '), spaceState);
    }

    /**
     * Function returns lexer default state
     *
     * @return default lexer state
     */
    public LexerState getDefaultState() {
        return defaultState;
    }

    /**
     * Function gets state and returns it
     *
     * @param currentState  - current lexer state
     * @param currentSymbol - current symbol (signal)
     * @return lexer state
     */
    public IState getNextState(final IState currentState, final char currentSymbol) {
        if (lexerStates.containsKey(new Pair<>(currentState, currentSymbol))) {
            return lexerStates.get(new Pair<>(currentState, currentSymbol));
        } else {
            return lexerStates.get(new Pair<>(currentState, (Character) null));
        }
    }

}
