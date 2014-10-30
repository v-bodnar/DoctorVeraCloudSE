/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kiev.doctorvera.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Config instance;
    private static Properties properties = new Properties();
    private static final String BUNDLE_NAME = File.separator + "ua" + File.separator + "kiev" + File.separator + "doctorvera" + File.separator + "config";
    private static File file = new File(BUNDLE_NAME + ".xml");
    public static enum Key { DATASOURCE_JNDI, COUNTRY, LANGUAGE }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
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
	
	public Boolean setProperty(String key, String value){
		try{
			FileOutputStream fileOutput = new FileOutputStream(file);
			properties.setProperty(key, value);
			properties.storeToXML(fileOutput, "Configuration file", "UTF-8");
			fileOutput.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

