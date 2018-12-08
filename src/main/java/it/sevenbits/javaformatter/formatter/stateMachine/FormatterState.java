package it.sevenbits.javaformatter.formatter.stateMachine;

import it.sevenbits.javaformatter.stateMachineSupport.IState;

import java.util.Objects;

/**
 * Class state for lexer
 */
public class FormatterState implements IState {
    private String state;

    /**
     * FormatterState constructor with one parameter
     *
     * @param state - state description
     */
    FormatterState(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormatterState that = (FormatterState) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
