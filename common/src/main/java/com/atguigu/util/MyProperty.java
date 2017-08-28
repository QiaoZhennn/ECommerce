package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperty {
    public static String getMyProperty(String url,String key){
        Properties properties = new Properties();
        InputStream inputStream = MyProperty.class.getClassLoader().getResourceAsStream(url);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
