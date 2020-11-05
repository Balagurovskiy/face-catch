package com.my.app.face_catch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
* Simple property reader
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class Property{
	
    /**
     * instance of Properties class
     */
	private final Properties PROPERTY = new Properties();
    /**
     * flag for class activity
     */
	private boolean active;
    /**
     * Constructor with argument catches IOException to handle cases when input path for property file is invalid
     * @param file - path for *.properties file
     * @return nothing
     */
	public Property(String file) {
		active = true;
	    try {
	    	PROPERTY.load(new FileInputStream( file ));
	    } catch (IOException e) {
	    	System.out.println("settings.properties -  FILE NOT FOUND");
	    	active = false;
	    }
	}
    /**
     * {@link Property#PROPERTY}
     * @param key - key to get value from properties
     * @return property value or null if there was an exception while loading the properties file 
     */
	public String getProperty(String key) {
		if(active) {
			return PROPERTY.getProperty(key);
		}
		return null;
	}
}
