package com.my.app.face_catch.services.visual_algorithms;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;


public interface VisualSearchAlgorithm{
	public List<SearchResult> find(Mat mat);
}
