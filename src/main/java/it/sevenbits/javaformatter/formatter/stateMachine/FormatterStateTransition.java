package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.properties.ConfigException;
import it.sevenbits.javaformatter.stateMachineSupport.IState;
import it.sevenbits.javaformatter.lexer.token.IToken;

/**
 * Class translator for formatter states (part between Formatter and FormatterStatesMap)
 */
public class FormatterStateTransition {
    private FormatterStateMap formatterStateMap;

    /**
     * FormatterStateTransition constructor with no parameters
     *
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public FormatterStateTransition() throws ConfigException {
        formatterStateMap = new FormatterStateMap();
    }

    /**
     * Function gets and return next formatter state
     *
     * @param currentState - current formatter state
     * @param currentToken - current token (signal)
     * @return next formatter state
     */
    public IState getNextState(final IState currentState, final IToken currentToken) {
        return formatterStateMap.getNextState(currentState, currentToken);
    }

    /**
     * Function gets and returns start state for Formatter
     *
     * @return start state
     */
    public IState getDefaultState() {
        return formatterStateMap.getDefaultState();
    }
}
