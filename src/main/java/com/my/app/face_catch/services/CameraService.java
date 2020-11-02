package com.my.app.face_catch.services;


import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import com.my.app.face_catch.services.builder.VusialServiceBuilder;
import com.my.app.face_catch.services.visual_algorithms.SearchResult;
import com.my.app.face_catch.services.visual_algorithms.VisualSearchAlgorithm;

public class CameraService implements VisualService {

	private Mat image;
	
	public CameraService(VusialServiceBuilder builder) {
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
	public void execute() {
	}

}
