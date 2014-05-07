package com.xincao.common_configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author caoxin
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new FileSystemXmlApplicationContext("./src/main/resources/app.xml");
    }
}
