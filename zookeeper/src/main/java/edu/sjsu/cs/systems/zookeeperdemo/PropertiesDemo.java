package edu.sjsu.cs.systems.zookeeperdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) throws IOException {
        File f = new File("/Users/sushantmane/systems/zookeeper/src/main/resources/tail-repl.properties");
        String file = f.toString();
        FileInputStream fis = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fis);
        System.out.println(properties.getProperty("server.1"));
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        fis.close();
    }
}
