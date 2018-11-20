package it.sevenbits.javaformatter.formatter;

import it.sevenbits.javaformatter.lexer.LexerException;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.io.input.ReaderException;
import it.sevenbits.javaformatter.io.input.IReader;
import it.sevenbits.javaformatter.io.output.IWriter;

/**
 * IFormatter interface with functions for formatting source Java code
 */
public interface IFormatter {

    /**
     * Function's contract for formatting source Java code
     *
     * @param reader is needed for reading source code from somewhere (depends on implementation)
     * @param writer is needed for writing formatted code to somewhere (depends on implementation)
     * @throws WriterException is thrown, if something goes wrong with writing
     * @throws ReaderException is thrown, if something goes wrong with reading
     * @throws LexerException is thrown, if something goes wrong with lexeme
     */
    void format(IReader reader, IWriter writer) throws WriterException, ReaderException, LexerException;
}
