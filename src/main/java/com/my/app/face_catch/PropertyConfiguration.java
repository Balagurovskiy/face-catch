package com.my.app.face_catch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.app.face_catch.detectors.face.CaffeDetector;
import com.my.app.face_catch.detectors.face.CascadeDetector;
import com.my.app.face_catch.detectors.face.FaceDetector;
/**
* Configuration for properties
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@Configuration
public class PropertyConfiguration {
	private final String PROPERTY_FILE = "src/main/resources/settings.properties";
	@Bean
	public Property property() {
		return new Property( PROPERTY_FILE );
	}
}
