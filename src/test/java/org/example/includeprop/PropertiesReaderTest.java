package org.example.includeprop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PropertiesReaderTest {

    @Test
    void getProperty() throws IOException {
        PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
        String property = reader.getProperty("my.external.property");
        Assertions.assertEquals("D:\\one.properties", property);
    }
}