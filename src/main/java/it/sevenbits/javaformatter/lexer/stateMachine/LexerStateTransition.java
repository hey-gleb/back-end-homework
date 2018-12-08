package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;

/**
 * Class translator for lexer states (part between Lexer and LexerStatesMap)
 */
public class LexerStateTransition {
    private final LexerStateMap stateMap;

    /**
     * LexerStateTransition constructor with no parameters
     *
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public LexerStateTransition() throws ConfigException {
        stateMap = new LexerStateMap();
    }

    /**
     * Function gets and return next lexer state
     *
     * @param currentState - current lexer state
     * @param symbol       - current symbol (signal)
     * @return next lexer state
     */
    public IState getNextState(final IState currentState, final char symbol) {
        return stateMap.getNextState(currentState, symbol);
    }

    /**
     * Function gets and returns start state
     *
     * @return start state
     */
    public LexerState getStartState() {
        return stateMap.getDefaultState();
    }
}
