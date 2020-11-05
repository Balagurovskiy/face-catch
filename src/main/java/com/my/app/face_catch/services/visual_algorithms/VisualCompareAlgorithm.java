package com.my.app.face_catch.services.visual_algorithms;

import org.opencv.core.Mat;
/**
* Interface for visual compare algorithms
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public interface VisualCompareAlgorithm {
	double compare(final Mat a, final Mat b);
}
