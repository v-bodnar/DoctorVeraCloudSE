/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Message {

    private static Message instance;
    private static Properties properties = new Properties();
    private static final String BUNDLE_NAME = File.separator + "ua" + File.separator + "kiev" + File.separator + "doctorvera" + File.separator + "message";
    private static final String language = Config.getInstance().getProperty(Config.Key.LANGUAGE); 
    private static File file = new File(BUNDLE_NAME + "_" + language + ".xml");
    
    public static enum Key { LOGIN_ERROR, IO_EXCEPTION, SERVLET_EXCEPTION, COMMAND_MISSING }

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            createProperties();
        }
        return instance;
    }

	private static void createProperties(){
		try {
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
