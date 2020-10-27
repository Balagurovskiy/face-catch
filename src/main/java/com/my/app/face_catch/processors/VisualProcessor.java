package com.my.app.face_catch.processors;

import java.util.List;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public interface VisualProcessor {
	List<Rect> executeSearch(VisualSearchAlgorithm vsa);
	
	void addRectToMat(Rect rec, Scalar color);
	void addFilledRectToMat(Rect rec, Scalar color);
	void addTextToMat(Point rec, Scalar color, String text);
	
	void createImage();
}
