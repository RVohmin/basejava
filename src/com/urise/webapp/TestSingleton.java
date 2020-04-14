package com.urise.webapp;

/**
 * basejava com.urise.webapp.TestSinleton
 *
 * @author romanvohmin
 * @since 14.04.2020 20:00
 */
public class TestSingleton {
    private static final TestSingleton SINGLETON = new TestSingleton();

    public static TestSingleton getInstance() {
        return SINGLETON;
    }

    private TestSingleton(){
    }
}
