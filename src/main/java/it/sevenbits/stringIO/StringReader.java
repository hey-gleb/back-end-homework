package it.sevenbits.stringIO;

import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.interfaces.IReader;

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

    /**
     * Show is there another symbol or not
     *
     * @return true if there is next symbol. False if there is no symbols left
     */
    public boolean hasNext() {
        return symbolPosition < mainString.length();
    }

    /**
     * Read one symbol from string
     *
     * @return read symbol
     */
    public char read() {
        char currentSymbol = mainString.charAt(symbolPosition);
        symbolPosition++;
        return currentSymbol;
    }
}
