package com.xincao.common.configuration;

/**
 *
 * @author caoxin
 */
public class Main {

    public static void main(String[] args) {
        ConfigAboutProperties c  = new ConfigAboutProperties();
        c.setPath("./config/");
        c.initConfigAboutPropertiesFromDirectory();
        c.load(Config.class);
    }
}