package com.my.app.face_catch.services.visual_algorithms;

import org.opencv.core.Mat;

public interface VisualCompareAlgorithm {
	double compare(final Mat a, final Mat b);
}
