package ua.kiev.doctorvera.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public class Mapping {
	private static Mapping instance;
	private ResourceBundle resource;
	private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.mapping";

    public static enum Key { 
    	MAIN, 
    	LOGIN_ERROR, 
    	LOGIN_PAGE,
    	USERS_PAGE,
    	ADD_USER_PAGE,
    	EDIT_USER_PAGE,
    	USER_GROUPS_PAGE
    }

	public static Mapping getInstance() {
		if (instance == null) {
			instance = new Mapping();
			instance.resource = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new ExtendedControl());
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public String getProperty(Enum key) {
		return (String) resource.getObject(key.toString());
	}
}
