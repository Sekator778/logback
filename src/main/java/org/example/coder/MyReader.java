package org.example.coder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
/**
 * locale.getpreferredencoding()
 */
public class MyReader {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = MyReader.class.getResourceAsStream("/resources/file.xml");
        byte[] bytes = inputStream.readAllBytes();
        String text = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(text); // выводим результат
    }
}
