package ua.kiev.doctorvera.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Mapping {
	
    private static Mapping instance;
    private static Properties properties = new Properties();
    private static final String BUNDLE_NAME =  File.separator + "ua" + File.separator + "kiev" + File.separator + "doctorvera" + File.separator + "manager" + File.separator + "mapping";
    public static enum Key { MAIN, LOGIN_ERROR, LOGIN_PAGE }
    private static File file = new File(BUNDLE_NAME + ".xml");
    
    public static Mapping getInstance() {
        if (instance == null) {
            instance = new Mapping();
            createProperties();
        }
        return instance;
    }

	private static void createProperties(){
		try {
			System.out.println(System.getProperty("user.dir"));
			System.out.println(file.getCanonicalPath());
			FileInputStream fileInput = new FileInputStream(file);
			properties.loadFromXML(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProperty(Key key){
		return properties.getProperty(key.toString());
	}
}
