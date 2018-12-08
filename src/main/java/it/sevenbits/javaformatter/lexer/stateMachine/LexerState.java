package it.sevenbits.javaformatter.lexer.stateMachine;

import it.sevenbits.javaformatter.stateMachineSupport.IState;

import java.util.Objects;

/**
 * Class state for lexer
 */
public class LexerState implements IState {
    private final String currentState;

    /**
     * LexerState constructor with one parameter
     *
     * @param state - state description
     */
    LexerState(final String state) {
        this.currentState = state;
    }

    @Override
    public String toString() {
        return currentState;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LexerState that = (LexerState) o;
        return Objects.equals(currentState, that.currentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }
}
