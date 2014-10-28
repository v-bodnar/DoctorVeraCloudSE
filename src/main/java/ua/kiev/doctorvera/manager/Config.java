/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.util.ResourceBundle;

/**
 *
 * @author MAXIM
 */
public class Config {

    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.config";
    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String MAIN = "MAIN";
    public static final String ERROR = "ERROR";
    public static final String LOGIN = "LOGIN";
    public static String PASSWORD = "PASSWORD";
    public static String LOGIN_PAGE = "LOGIN_PAGE";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
