package com.my.app.face_catch.services.search_algorithm;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;


public interface VisualSearchAlgorithm{
	public List<Rect> find(Mat mat);
}
