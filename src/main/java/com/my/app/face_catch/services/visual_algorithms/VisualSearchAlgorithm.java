package com.my.app.face_catch.services.visual_algorithms;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
* Interface for visual search algorithms
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public interface VisualSearchAlgorithm{
	public List<SearchResult> find(Mat mat);
}
