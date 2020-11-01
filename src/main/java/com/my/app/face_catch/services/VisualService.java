package com.my.app.face_catch.services;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public interface VisualService {
	List<SearchResult> executeSearch();
	public Mat getImage();
	void export(String path);
}
