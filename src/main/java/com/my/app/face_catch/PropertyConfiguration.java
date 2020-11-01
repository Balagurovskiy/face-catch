package com.my.app.face_catch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.app.face_catch.detectors.CaffeDetector;
import com.my.app.face_catch.detectors.CascadeDetector;
import com.my.app.face_catch.detectors.FaceDetector;

@Configuration
public class PropertyConfiguration {
	private final String PROPERTY_FILE = "src/main/resources/settings.properties";
	@Bean
	public Property property() {
		return new Property( PROPERTY_FILE );
	}
}
