package org.example.includeprop;

import org.example.Logback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);

    public PropertiesReader(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        if (is == null) {
            LOGGER.error("check file with properties: {}", propertyFileName);
            System.exit(-1);
        }
        this.properties.load(is);
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}