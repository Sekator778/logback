package org.example.internalization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Constants {
    public static int COUNT_DEPARTMENTS;
    public static String MSG_TOTAL_AMOUNT;
    public static Properties PROPS;
    public static ResourceBundle UI_LANGUAGE;

    static {
        Logger log = LoggerFactory.getLogger(Constants.class);
        PROPS = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\projects\\logback\\src\\main\\resources\\message_ru.properties\n" +
                    "C:\\projects\\logback\\src\\main\\resources\\message_eng.properties");
            PROPS.load(fis);
            fis.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        if (PROPS.getProperty("ui.language").equalsIgnoreCase("RUS"))
            UI_LANGUAGE = ResourceBundle.getBundle("messages", CharsetControl.RUS);
        else
            UI_LANGUAGE = ResourceBundle.getBundle("messages", CharsetControl.ENG);

        MSG_TOTAL_AMOUNT = UI_LANGUAGE.getString("msg.total.amount");
        COUNT_DEPARTMENTS = Integer.parseInt(PROPS.getProperty("count.departments"));
    }
}