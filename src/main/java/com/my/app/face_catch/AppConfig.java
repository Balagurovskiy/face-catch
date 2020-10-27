package com.my.app.face_catch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.app.face_catch.detectors.CaffeDetector;
import com.my.app.face_catch.detectors.CascadeDetector;
import com.my.app.face_catch.detectors.FaceDetector;

@Configuration
public class AppConfig {
	
	@Bean
	public Property property() {
		return new Property();
	}
	
	@Bean(name="cascade")
	public FaceDetector cascadeDetector() {
		return new CascadeDetector( property() );
	}
	@Bean(name="caffe")
	public FaceDetector caffeDetector() {
		return new CaffeDetector( property() );
	}
}
