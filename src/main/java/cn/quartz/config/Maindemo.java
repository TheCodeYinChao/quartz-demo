package cn.quartz.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Admin on 2018/6/17.
 */
public class Maindemo {
    public static void main(String[] args) {
        Properties pps = new Properties();
        try {
            String path = ClassLoader.getSystemClassLoader().getResource("config/quartz.properties").getPath();
            pps.load(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
