package com.my.app.face_catch;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Property{
	
	
	private final Properties PROPERTY = new Properties();
	
	private boolean active;
	
	public Property(String file) {
		active = true;
	    try {
	    	PROPERTY.load(new FileInputStream( file ));
	    } catch (IOException e) {
	    	System.out.println("settings.properties -  FILE NOT FOUND");
	    	active = false;
	    }
	}
	
	public String getProperty(String key) {
		if(active) {
			return PROPERTY.getProperty(key);
		}
		return null;
	}
	

}
