package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.includeprop.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Зібрати мавеном jar і запустити саме як jar програму, яка видасть json такого виду
 * {“message”: “Привіт <текст із проперті-файлу, властивість username=Ваше ім'я> !”}
 * Використайте бібліотеку jackson-databind
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * write JSObject to file
     */
    public void writeJObjectToFile(String fileName) {
        List<String> properties = readProperties(fileName);

        String messageData = "Привіт " + properties.get(0);  // build string for answer
        LOGGER.info("build message to json  =  {}", messageData);
        String usernameData = "Sekator";
        LOGGER.info("build message to json  =  {}", usernameData);
        /* build json */
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode object = mapper.createObjectNode();
        object.put("message", messageData);
        object.put("username", usernameData);
        LOGGER.info("build jsonObject {}", object);
        String outputFileName = properties.get(1);
        LOGGER.info("file name to save {}", outputFileName);

        try {
            mapper.writeValue(Paths.get(outputFileName).toFile(), object); // write json to file
            LOGGER.info("save json to file =  {}", outputFileName);
        } catch (Exception ex) {
            LOGGER.error("IOException {}", ex.getMessage());
        }
    }

    /**
     * read path to file with custom property
     * field my.external.property in pom.xml
     */
    public void readPathToExternalPropertiesFile() {
        try {
            PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
            String fileName = reader.getProperty("my.external.property");
            writeJObjectToFile(fileName);
        } catch (IOException e) {
            LOGGER.error("IOException when read properties-from-pom.properties");
        }
    }

    /**
     * read property file and fill list
     *
     * @param fileName - nameFile
     * @return list with value of property
     */
    private List<String> readProperties(String fileName) {
        Properties appProps = new Properties(); // read properties file
        try {
            LOGGER.info("read file {}", fileName);
            appProps.load(Files.newInputStream(Paths.get(fileName))); // where external file
        } catch (IOException e) {
            LOGGER.error("IOException with file name {}", fileName);
        }
        LOGGER.info("read file {}", fileName);
        List<String> result = new ArrayList<>();
        result.add(appProps.getProperty("data"));  // get specific property
        LOGGER.info("read specific property \"data\" =  {}", result.get(0));
        result.add(appProps.getProperty("file"));  // get specific property
        LOGGER.info("read specific property \"file\" =  {}", result.get(1));

        return result;
    }


    /**
     * point for enter
     */
    public static void main(String[] args) throws IOException {
        new Main().readPathToExternalPropertiesFile();
    }
}
