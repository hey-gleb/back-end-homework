package it.sevenbits.javaformatter.formatter;

/**
 * Class describes indent level in formatter
 */
public class IndentLevel {
    private int level;

    /**
     * IndentLevel constructor with no parameters
     */
    IndentLevel() {
        level = 0;
    }

    public int getLevel() {
        return level;
    }

    /**
     * Function increments level value
     */
    public void levelIncrement() {
        level++;
    }

    /**
     * Function decrements level value
     */
    public void levelDecrement() {
        level--;
    }
}
