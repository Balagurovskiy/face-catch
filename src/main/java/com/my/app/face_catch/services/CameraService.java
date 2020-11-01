package com.my.app.face_catch.services;


import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class CameraService implements VisualService {

	private Mat image;
	private VisualSearchAlgorithm vsa;
	
	public CameraService(VisualSearchAlgorithm vsa) {
//        VideoCapture capture = new VideoCapture();
//        capture.set(CAP_PROP_FRAME_WIDTH, 1280);
//        capture.set(CAP_PROP_FRAME_HEIGHT, 720);
//
//        if (!capture.open(0)) {
//            System.out.println("Can not open the cam !!!");
//        }
//		while (capture.read(image)) {}
	}
	@Override
	public List<SearchResult> executeSearch() {
		return  new ArrayList<SearchResult>();
	}
	@Override
	public void export(String path) {
	}
	@Override
	public Mat getImage() {
		return null;
	}

}
