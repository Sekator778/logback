package org.example.coder;

import java.io.*;
import java.util.Properties;

public class Reader {
    public static void main(String[] args) {
        new Reader().printAll("different.properties");
    }

    private void printAll(String filename) {
        revertFile("src/main/resources/different.properties");
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);
            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void revertFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String ascii2Native = Native2AsciiUtils.native2Ascii(builder.toString());
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(ascii2Native);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
