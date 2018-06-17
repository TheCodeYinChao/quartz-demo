package cn.quartz.config;

import org.aspectj.apache.bcel.util.ClassPath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Admin on 2018/6/17.
 */
public class Maindemo {
    public static void main(String[] args) throws Exception{
        String classPath = ClassPath.getSystemClassPath().getPath("config/quartz.properties");
        System.out.println(classPath);
        Properties pps = new Properties();
        try {
            String path = ClassLoader.getSystemClassLoader().getResource("config/quartz.properties").getPath();
            pps.load(new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
