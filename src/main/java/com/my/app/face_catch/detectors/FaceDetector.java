package com.my.app.face_catch.detectors;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import com.my.app.face_catch.processors.VisualSearchAlgorithm;

public interface FaceDetector{
	public List<Rect> find(Mat mat);
}
