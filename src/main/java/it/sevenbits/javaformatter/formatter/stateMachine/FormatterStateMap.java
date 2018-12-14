package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.stateMachineSupport.Pair;
import it.sevenbits.javaformatter.lexer.token.IToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains formatter states
 */
public class FormatterStateMap {
    private final IState defaultState = new FormatterState("DEFAULT");
    private final Map<Pair<IState, String>, IState> stateMap;

    /**
     * FormatterStateMap constructor with no parameters
     *
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public FormatterStateMap() throws ConfigException {
        Config config = Config.getInstance();
        stateMap = new HashMap<>();

        IState spaceAllowed = new FormatterState(config.getProperty("FORMATTER_STATE_SPACE_ALLOWED"));
        IState singleComment = new FormatterState(config.getProperty("FORMATTER_STATE_SINGLELINE_COM"));
        IState multiComment = new FormatterState(config.getProperty("FORMATTER_STATE_MULTILINE_COM"));

        stateMap.put(new Pair<>(defaultState, null), defaultState);
        stateMap.put(new Pair<>(defaultState, "TEXT"), spaceAllowed);
        stateMap.put(new Pair<>(defaultState, "SINGLE_LINE_COMMENT"), singleComment);
        stateMap.put(new Pair<>(defaultState, "MULTI_LINE_COMMENT"), multiComment);

        stateMap.put(new Pair<>(spaceAllowed, "TEXT"), spaceAllowed);
        stateMap.put(new Pair<>(spaceAllowed, "SINGLE_LINE_COMMENT"), singleComment);
        stateMap.put(new Pair<>(spaceAllowed, "MULTI_LINE_COMMENT"), multiComment);

        stateMap.put(new Pair<>(singleComment, null), singleComment);
        stateMap.put(new Pair<>(singleComment, "NEW_LINE"), defaultState);

        stateMap.put(new Pair<>(multiComment, null), multiComment);
        stateMap.put(new Pair<>(multiComment, "END_MULTI_LINE_COMMENT"), defaultState);
    }

    /**
     * Function returns formatter default state
     *
     * @return default formatter state
     */
    public IState getDefaultState() {
        return defaultState;
    }

    /**
     * Function gets state and returns it
     *
     * @param state  - current formatter state
     * @param signal - current token (signal)
     * @return formatter state
     */
    public IState getNextState(final IState state, final IToken signal) {
        if (stateMap.containsKey(new Pair<>(state, signal.getName()))) {
            return stateMap.get(new Pair<>(state, signal.getName()));
        } else {
            return stateMap.getOrDefault(new Pair<>(state, (String) null), defaultState);
        }
    }
}
