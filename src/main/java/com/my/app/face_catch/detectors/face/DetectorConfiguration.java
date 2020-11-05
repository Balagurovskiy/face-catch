package com.my.app.face_catch.detectors.face;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.my.app.face_catch.Property;
/**
* Detector configuration
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
@Configuration
public class DetectorConfiguration {
	
	@Autowired
	Property property;
	
	private static final String CASCADE_FILE = "opencv.cascade";
	
    private static final String PROTO_FILE = "opencv.proto";
    private static final String CAFFE_MODEL_FILE = "opencv.caffe-model";
	
	@Bean(name="cascade")
	public FaceDetector cascadeDetector() {
		
		String cascade = property.getProperty(CASCADE_FILE);
		
		if (cascade == null) {
			System.out.println("CASCADE DETECTOR FAILED - PROPERRTY DOES NOT EXIST");
			
			return new CascadeDetector();
		}
		
		return new CascadeDetector( cascade );
	}
	
	
	@Lazy
	@Bean(name="caffe")
	public FaceDetector caffeDetector() {
		//CHECK PROPERTY
		return new CaffeDetector(  property.getProperty(PROTO_FILE),  property.getProperty(CAFFE_MODEL_FILE) );
	}
}
