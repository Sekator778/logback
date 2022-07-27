package org.example.properies.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ReadXMLProperties {
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.loadFromXML(ReadXMLProperties.class.getClassLoader()
                .getResourceAsStream("prop.xml"));
        String propA = prop.getProperty("propA");
        System.out.println(propA);
        String propB = prop.getProperty("propB");
        System.out.println(propB);
        String propC = prop.getProperty("propC");
        System.out.println(propC);
        String propD = prop.getProperty("propD");
        System.out.println(propD);
        String propE = prop.getProperty("propE");
        System.out.println(propE);
        String propF = prop.getProperty("propF");
        System.out.println(propF);
        FileWriter writer = new FileWriter("ukr.txt");
        writer.write(propA);
        writer.write("\n");
        writer.write(propB);
        writer.write("\n");
        writer.write(propC);
        writer.write("\n");
        writer.write(propD);
        writer.write("\n");
        writer.write(propE);
        writer.write("\n");
        writer.write(propF);
        writer.flush();
        writer.close();
    }
}
