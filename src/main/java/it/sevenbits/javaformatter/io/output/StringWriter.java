package it.sevenbits.javaformatter.io.output;

/**
 * Implementation of IWriter interface
 */
public class StringWriter implements IWriter {
    private final StringBuilder stringBuilder;
    private final int maxChar = 255;
    private final int minChar = 0;

    /**
     * StringWriter constructor with no parameters
     */
    public StringWriter() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Write one symbol into stringBuilder;
     *
     * @param symbol - char that need to be written into stringBuilder
     * @throws WriterException is thrown, if something goes wrong with writing
     */
    public void write(final char symbol) throws WriterException {
        if (symbol < minChar || symbol > maxChar) {
            throw new WriterException("Wrong char");
        }
        stringBuilder.append(symbol);
    }

    /**
     * Convert stringBuilder to String
     *
     * @return converted stringBuilder as String
     */
    public String getString() {
        return stringBuilder.toString();
    }
}
