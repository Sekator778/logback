package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Paths;

/**
 * Зібрати мавеном jar і запустити саме як jar програму, яка видасть json такого виду
 * {“message”: “Привіт <текст із проперті-файлу, властивість username=Ваше ім'я> !”}
 * Використайте бібліотеку jackson-databind
 */
public class Main {
    public void writeJObjectToFile() {
        String messageData = "Привіт " + ReaderProperties.getInstance().getProperty("data");
        String usernameData = "Sekator";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode object = mapper.createObjectNode();
        object.put("message", messageData);
        object.put("username", usernameData);
        try {
            mapper.writeValue(Paths.get("user.json").toFile(), object);
        } catch (Exception ex) {
            System.out.println("ex");
        }
    }

    public static void main(String[] args) {
        new Main().writeJObjectToFile();
    }

}
