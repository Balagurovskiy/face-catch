package com.my.app.face_catch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.my.app.face_catch.detectors.FaceDetector;

@Configuration
public class AlgorithmConfiguration {

	@Bean(name="cascadeFDA")
	public VisualSearchAlgorithm cascadeFaceDetectorAlgorithm(@Qualifier("cascade")FaceDetector fd) {
		return (VisualSearchAlgorithm)fd;
	}
	@Lazy
	@Bean(name="caffeFDA")
	public VisualSearchAlgorithm caffeFaceDetectorAlgorithm(@Qualifier("caffe")FaceDetector fd) {
		return (VisualSearchAlgorithm)fd;
	}
}
