package com.my.app.face_catch.services.visual_algorithms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.my.app.face_catch.comparator.MatrixComparator;
import com.my.app.face_catch.detectors.face.FaceDetector;

@Configuration
public class VisualAlgorithmConfiguration {

	@Bean(name="cascadeFDA")
	public VisualSearchAlgorithm cascadeFaceDetectorAlgorithm(@Qualifier("cascade")FaceDetector fd) {
		return (VisualSearchAlgorithm)fd;
	}
	
	@Bean(name="matrixCA")
	public VisualCompareAlgorithm matrixCompareAlgorithm(@Qualifier("matrixCmp")MatrixComparator mc) {
		return (VisualCompareAlgorithm) mc;
	}
	
	@Lazy
	@Bean(name="caffeFDA")
	public VisualSearchAlgorithm caffeFaceDetectorAlgorithm(@Qualifier("caffe")FaceDetector fd) {
		return (VisualSearchAlgorithm)fd;
	}
}
