package com.my.app.face_catch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Property {
	private final String PROPERTY_FILE = "src/main/resources/settings.properties";
	private final Properties PROPERTY = new Properties();
	private boolean status;
	
	
	public Property() {
		status = true;
	    try {
	    	PROPERTY.load(new FileInputStream( PROPERTY_FILE ));
	    } catch (IOException e) {
//	    	MAKE LOG - PROPERTY FILE NOT FOUND
	    	System.out.println("settings.properties -  FILE NOT FOUND");
	    	status = false;
	    }
	}
	
	public boolean getStatus() {
		return status;
	}
	public String getProperty(String key) {
		if(status) {
			return PROPERTY.getProperty(key);
		}
		return null;
	}
	

}
