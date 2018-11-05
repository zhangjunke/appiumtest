package com.junker.appiumtest.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class MyProperties {
    public static String getProperties(String key) throws IOException {
        String value="";
        AtomicReference<Properties> prop = new AtomicReference<>(new Properties());
        InputStream in = MyProperties.class.getClassLoader().getResourceAsStream(
                "settings.properties");
        prop.get().load(in);
        value=prop.get().getProperty(key);
        return value;
    }
}
