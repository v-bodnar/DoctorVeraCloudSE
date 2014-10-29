/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Artem
 */
public class Message {

    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.messages";
    public static final String SERVLET_EXECPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String COMMAND_MISSING = "COMMAND_MISSING";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
