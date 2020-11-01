package com.my.app.face_catch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.my.app.face_catch.Property;

@Configuration
public class ServiceConfiguration {

	@Autowired
	Property property;
	
	private static final String IMAGE_SOURCE = "opencv.input";
	
	private static final String SOME_IMAGE_EX = "IMAGE PROCESSOR FAILED - PROPERRTY DOES NOT EXIST";

	@Bean(name="imageServiceCascade")
	public VisualService imageServiceWithCascades(@Qualifier("cascadeFDA")VisualSearchAlgorithm vsa) {
		final String IMAGE_PROPERTY = property.getProperty(IMAGE_SOURCE);
		if (IMAGE_PROPERTY == null) {
	       //LOG HERE
			System.out.println(SOME_IMAGE_EX);
			return new ImageService();
		}
		return new ImageService( IMAGE_PROPERTY, vsa);
	}
	
	@Lazy
	@Bean(name="imageServiceCaffe")
	public VisualService imageServiceWithCafee(@Qualifier("caffeFDA")VisualSearchAlgorithm vsa) {
		final String IMAGE_PROPERTY = property.getProperty(IMAGE_SOURCE);
		if (IMAGE_PROPERTY == null) {
	       //LOG HERE
			System.out.println(SOME_IMAGE_EX);
			return new ImageService();
		}
		return new ImageService( IMAGE_PROPERTY, vsa);
	}
	
	@Lazy
	@Bean(name="cameraServiceCascade")
	public VisualService cameraServiceWithCascades(@Qualifier("cascadeFDA")VisualSearchAlgorithm vsa) {
		return new CameraService(vsa);
	}
	@Lazy
	@Bean(name="cameraServicecCaffe")
	public VisualService cameraServiceWithCaffe(@Qualifier("caffeFDA")VisualSearchAlgorithm vsa) {
		return new CameraService(vsa);
	}
}
