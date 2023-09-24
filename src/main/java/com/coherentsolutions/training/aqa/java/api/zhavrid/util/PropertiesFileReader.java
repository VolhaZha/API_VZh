package com.coherentsolutions.training.aqa.java.api.zhavrid.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
    private static Properties prop;
    private static final String PROPERTIES_PATH = "src/main/resources/testdata.properties";

    private PropertiesFileReader() {
    }

    public static synchronized Properties getProperties() {
        if (prop == null) {
            try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {
                prop = new Properties();
                prop.load(input);
            } catch (IOException ex) {
                String errorMessage = String.format("Cannot read property file: %s", PROPERTIES_PATH);
                throw new RuntimeException(errorMessage, ex);
            }
        }
        return prop;
    }

    public static String getProperty(PropertyKey key) {
        return getProperties().getProperty(key.getKey());
    }

}
