package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final String USERNAME = "username";
    private static final String PROP_FILE_NAME = "app.properties";
    private static final String WRITE_JSON = "write.json";
    private static final String WRITE_XML = "write.xml";
    private static final String TRUE = "true";
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public App() {
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Properties prop = new Properties();
        File file = new File("./app.properties");
        if (file.exists()) {
            try {
                InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

                try {
                    prop.load(in);
                } catch (Throwable var8) {
                    try {
                        in.close();
                    } catch (Throwable var6) {
                        var8.addSuppressed(var6);
                    }

                    throw var8;
                }

                in.close();
            } catch (IOException var9) {
                var9.printStackTrace();
                logger.warn("No 'app.property' file");
            }
        } else {
            try {
                prop.load(new InputStreamReader(App.class.getClassLoader().getResourceAsStream("app.properties"), StandardCharsets.UTF_8));
            } catch (IOException var7) {
                var7.printStackTrace();
                logger.warn("No 'app.properties' near jar. So using default file");
            }
        }

        writeToConsole(prop);
    }

    private static void writeToConsole(Properties prop) {
        if (prop.containsKey("username")) {
            String username = prop.getProperty("username");

            try {
                if (prop.containsKey("write.json")) {
                    writeJSON(prop, username);
                } else {
                    logger.info("No 'write.json' in property file");
                }

                if (prop.containsKey("write.xml")) {
                    writeXML(prop, username);
                } else {
                    logger.info("No 'write.xml' in property file");
                }
            } catch (JsonProcessingException var3) {
                var3.printStackTrace();
                logger.warn("Jackson can't serialized the object");
            }
        }

    }

    private static void writeXML(Properties prop, String username) throws JsonProcessingException {
        if (prop.getProperty("write.xml").equals("true")) {
            XmlMapper xmlMapper = new XmlMapper();
            String msg = xmlMapper.writeValueAsString(new User(username));
            System.out.println(msg);
            logger.trace("Xml message was printed out '" + msg + "'.");
        } else {
            logger.debug("Property 'write.xml' is not 'true'");
        }

    }

    private static void writeJSON(Properties prop, String username) throws JsonProcessingException {
        if (prop.getProperty("write.json").equals("true")) {
            ObjectMapper objectMapper = new ObjectMapper();
            String msg = objectMapper.writeValueAsString(new User(username));
            System.out.println(msg);
            logger.trace("Json message was printed out '" + msg + "'.");
        } else {
            logger.debug("Property 'write.json' is not 'true'");
        }

    }
}
