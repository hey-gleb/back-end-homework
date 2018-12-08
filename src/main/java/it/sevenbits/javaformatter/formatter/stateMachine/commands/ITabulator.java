package it.sevenbits.javaformatter.formatter.stateMachine.commands;

import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.properties.ConfigException;

/**
 * Create and add tabulation
 */
public interface ITabulator {

    /**
     * Function writes tabulation to writer
     *
     * @param writer - destination for writing
     * @param level  - current indent level
     * @throws CommandException is thrown if something goes wrong while executing command
     * @throws ConfigException is thrown if something goes wrong while reading config file
     * @throws WriterException is thrown if something goes wrong while writing symbols
     */
    void addTabulation(IWriter writer, int level) throws CommandException, ConfigException, WriterException;
}
