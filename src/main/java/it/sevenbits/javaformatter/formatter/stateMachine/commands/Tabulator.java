package it.sevenbits.javaformatter.formatter.stateMachine.commands;

import it.sevenbits.javaformatter.io.output.IWriter;
import it.sevenbits.javaformatter.io.output.WriterException;
import it.sevenbits.javaformatter.properties.Config;
import it.sevenbits.javaformatter.properties.ConfigException;

/**
 * Class for creating tabulation
 */
public class Tabulator implements ITabulator {

    @Override
    public void addTabulation(final IWriter writer, final int level) throws ConfigException, WriterException {
        Config config = Config.getInstance();
        for (int i = 0; i < level * Integer.parseInt(config.getProperty("TABULATION_LENGTH")); i++) {
            writer.write(' ');
        }
    }
}
