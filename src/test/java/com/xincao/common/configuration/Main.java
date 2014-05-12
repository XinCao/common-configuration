package com.xincao.common.configuration;

/**
 *
 * @author caoxin
 */
public class Main {

    public static void main(String[] args) {
        Config c  = new Config("./config/");
        c.load();
    }
}
