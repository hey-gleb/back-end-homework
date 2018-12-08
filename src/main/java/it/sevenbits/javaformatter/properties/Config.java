package it.sevenbits.javaformatter.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class for reading config file
 */
public class Config {
    private Properties property;
    private static Config instance;

    /**
     * Config constructor with no parameters
     *
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public Config() throws ConfigException {
        property = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            property.load(fis);
        } catch (IOException e) {
            throw new ConfigException("Can't read config file", e);
        }
    }

    /**
     * Singleton function
     *
     * @return same Config object
     * @throws ConfigException is thrown if something goes wrong with reading config file
     */
    public static Config getInstance() throws ConfigException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Function gets and returns value from config
     *
     * @param propertyName value name for config file
     * @return value from config file
     */
    public String getProperty(final String propertyName) {
        return property.getProperty(propertyName);
    }
}
