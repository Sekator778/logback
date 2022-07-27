package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.includeprop.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Зібрати мавеном jar і запустити саме як jar програму, яка видасть json такого виду
 * {“message”: “Привіт <текст із проперті-файлу, властивість username=Ваше ім'я> !”}
 * Використайте бібліотеку jackson-databind
 */
public class Main {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Main.class);

    public void writeJObjectToFile(String fileName) throws IOException {

        Properties appProps = new Properties(); // read properties file
        appProps.load(Files.newInputStream(Paths.get(fileName))); // where external file
        LOGGER.info("read file {}", fileName);
        String data = appProps.getProperty("data");  // get specific property
        LOGGER.info("read specific property data =  {}", data);
        String messageData = "Привіт " + data;  // build string for answer
        LOGGER.info("build message to json  =  {}", messageData);
        String usernameData = "Sekator";
        LOGGER.info("build message to json  =  {}", usernameData);
        /* build json */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode object = mapper.createObjectNode();
        object.put("message", messageData);
        object.put("username", usernameData);
        LOGGER.info("build jsonObject {}", object);
        String outputFileName = appProps.getProperty("file");  // get specific property
        LOGGER.info("file name to save {}", outputFileName);

        try {
            mapper.writeValue(Paths.get(outputFileName).getFileName().toFile(), object); // write json to file
            LOGGER.info("save json to file =  {}", outputFileName);
        } catch (Exception ex) {
            LOGGER.error("IOException {}", ex.getMessage());
        }
    }

    public void readPathToExternalPropertiesFile() throws IOException {
        PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
        String fileName = reader.getProperty("my.external.property");
        writeJObjectToFile(fileName);
    }


    /**
     * точка входу
     */
    public static void main(String[] args) throws IOException {
        new Main().readPathToExternalPropertiesFile();
    }
}
