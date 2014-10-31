/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

	private static Message instance;
	private ResourceBundle resource;
	private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.message";

	public static enum Key {
		LOGIN_ERROR, IO_EXCEPTION, SERVLET_EXCEPTION, COMMAND_MISSING
	}

	public static Message getInstance() {
		if (instance == null) {
			instance = new Message();
			instance.resource = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new ExtendedControl());
		}
		return instance;
	}

	public String getProperty(Key key) {
		return (String) resource.getObject(key.toString());
	}
}
