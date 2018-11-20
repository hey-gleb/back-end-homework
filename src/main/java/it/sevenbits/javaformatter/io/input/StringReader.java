package it.sevenbits.javaformatter.io.input;

/**
 * Implementation of IReader interface
 */
public class StringReader implements IReader {
    private String mainString;
    private int symbolPosition;

    /**
     * StringReader constructor with one parameter
     *
     * @param mainString - code string for modifying
     * @throws ReaderException is thrown, if something wrong with plain text
     */
    public StringReader(final String mainString) throws ReaderException {
        if (mainString == null) {
            throw new ReaderException("The text is empty");
        }
        this.mainString = mainString;
        this.symbolPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return symbolPosition < mainString.length();
    }

    @Override
    public char read() {
        char currentSymbol = mainString.charAt(symbolPosition);
        symbolPosition++;
        return currentSymbol;
    }
}
