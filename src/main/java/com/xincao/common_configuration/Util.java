package com.xincao.common_configuration;

/**
 * @author lord_rex
 *
 */
public class Util {

    /**
     * @param s
     */
    public static void printSection(String s) {
        s = "-[ " + s + " ]";
        while (s.length() < 79) {
            s = "=" + s;
        }
        System.out.println(s);
    }
}