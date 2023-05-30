package ru.msm.pm.dao.impls.file_dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileDaoUtils {

    static void setProperty(String propFilePath, String propName, String propValue) throws IOException {
        try (FileInputStream fis = new FileInputStream(propFilePath)) {
            Properties properties = new Properties();
            properties.load(fis);
            properties.setProperty(propName, propValue);
            try (FileOutputStream fos = new FileOutputStream(propFilePath)) {
                properties.store(fos, "set last id");
            }
        }
    }

    static String getProperty(String propFilePath, String propName) throws Exception {
        String property;
        try (FileInputStream fis = new FileInputStream(propFilePath)) {
            Properties properties = new Properties();
            properties.load(fis);
            property = properties.getProperty(propName);
        }
        if (property == null) {
            throw new Exception("There is no property with name \"" + propName + "\".");
        }
        if (property.equals("")) {
            throw new Exception("There is no value for property with name \"" + propName + "\".");
        }
        return property;
    }

}
