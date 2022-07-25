package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ReaderProperties {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReaderProperties.class);
    private final Properties configProp = new Properties();

    private ReaderProperties() {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        LOGGER.info("Reading all properties from the file");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Bill Pugh Solution for singleton pattern
    private static class LazyHolder {
        private static final ReaderProperties INSTANCE = new ReaderProperties();
    }

    public static ReaderProperties getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    public static void main(String[] args) {
        //Get individual properties
        System.out.println(ReaderProperties.getInstance().getProperty("data"));
        //All property names
        System.out.println(ReaderProperties.getInstance().getAllPropertyNames());
    }
}
