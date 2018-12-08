package it.sevenbits.javaformatter.stateMachineSupport;

import java.util.Objects;

/**
 * Pair class
 *
 * @param <T> - any data type
 * @param <V> - any date type
 */
public class Pair<T, V> {

    private final T first;
    private final V second;

    /**
     * Pair constructor with two parameters
     *
     * @param first  - first part of pair
     * @param second - second part of pair
     */
    public Pair(final T first, final V second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
