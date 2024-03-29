package ua.kiev.doctorvera.manager;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class Mapping extends ListResourceBundle{
	private static Mapping instance;
	private ResourceBundle resource;
	private static final String BUNDLE_NAME = "ua.kiev.doctorvera.manager.mapping";
	private static Object[][] entriesArray;
	
	public Mapping(){
		super();
		resource = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault(), new ExtendedControl());
		setList();
	}

    public static enum Page { 
    	MAIN, 
    	LOGIN_ERROR, 
    	LOGIN_PAGE,
    	SHOW_USERS_PAGE,
    	ADD_USER_PAGE,
    	EDIT_USER_PAGE,
    	SHOW_USER_TYPES_PAGE
    }
    
    public static enum Command { 
    	SHOW_USERS_COMMAND,
    	ADD_USER_COMMAND,
    	EDIT_USER_COMMAND,
    	SHOW_USER_TYPES_COMMAND
    }

	public static Mapping getInstance() {
		if (instance == null) {
			instance = new Mapping();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public String getProperty(Enum key) {
		return (String) resource.getObject(key.toString());
	}
	
	public String getProperty(String key) {
		return (String) resource.getObject(key);
	}
	
	public ResourceBundle getResource() {
		return resource;
	}
	
	public Object[][] getContents() {
		return entriesArray;
	}
	
	private void setList(){
		Set<String> keys = getResource().keySet();
		entriesArray = new Object[keys.size()][2];
		int i = 0;
		for(String key : keys){
		    entriesArray[i][0] = key;
		    entriesArray[i][1] = getProperty(key);
		    i++;
		}
	}
}
