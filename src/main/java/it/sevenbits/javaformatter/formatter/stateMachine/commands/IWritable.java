package it.sevenbits.javaformatter.formatter.stateMachine.commands;

import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.lexer.token.IToken;

/**
 * Interface for writing token to writer
 */
public interface IWritable {

    /**
     * Function writes token to writer
     *
     * @param writer - destination for writing
     * @param token  - object we will write
     * @throws WriterException is thrown if something goes wrong with writing
     */
    void write(IWriter writer, IToken token) throws WriterException;
}
