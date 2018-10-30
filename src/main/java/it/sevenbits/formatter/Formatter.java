package it.sevenbits.formatter;

import it.sevenbits.exceptions.ReaderException;
import it.sevenbits.exceptions.WriterException;
import it.sevenbits.interfaces.IReader;
import it.sevenbits.interfaces.IWriter;

/**
 * Class with functions for modifying code to correct one
 */
public class Formatter {
    private int level;
    private boolean isOneWhiteSpace;
    private boolean isNewLine;

    /**
     * Formatter's constructor with no parameters
     */
    public Formatter() {
        level = 0;
        isOneWhiteSpace = true;
        isNewLine = false;
    }

    /**
     * Modifies string with Java rules
     *
     * @param reader - any object implements IReader interface
     * @param writer - any object implements IWriter interface
     * @throws WriterException is thrown, if something goes wrong with writing result
     * @throws ReaderException is thrown, if something goes wrong with reading plain text
     */
    public void format(final IReader reader, final IWriter writer) throws WriterException, ReaderException {
        while (reader.hasNext()) {
            char symbol = reader.read();
            switch (symbol) {
                case '{':
                    if (isNewLine) {
                        addTabulation(writer);
                    }
                    level++;
                    writer.write(symbol);
                    writer.write('\n');
                    isNewLine = true;
                    break;
                case '}':
                    level--;
                    addTabulation(writer);
                    writer.write(symbol);
                    if (reader.hasNext()) {
                        writer.write('\n');
                    }
                    break;
                case ';':
                    writer.write(symbol);
                    writer.write('\n');
                    isNewLine = true;
                    isOneWhiteSpace = false;
                    break;
                case ' ':
                    if (isOneWhiteSpace) {
                        writer.write(symbol);
                        isOneWhiteSpace = false;
                    }
                    break;
                case '\n':
                    break;
                case '\t':
                    break;
                default:
                    if (isNewLine) {
                        addTabulation(writer);
                        isNewLine = false;
                    }
                    isOneWhiteSpace = true;
                    writer.write(symbol);
                    break;
            }
        }
    }

    private void addTabulation(final IWriter writer) throws WriterException {
        for (int i = 0; i < level; i++) {
            writer.write('\t');
        }
    }
}