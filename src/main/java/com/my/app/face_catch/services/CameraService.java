package com.my.app.face_catch.services;


import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import com.my.app.face_catch.services.builder.VusialServiceBuilder;
import com.my.app.face_catch.services.visual_algorithms.Image;
import com.my.app.face_catch.services.visual_algorithms.VisualSearchAlgorithm;
/**
* Service for operations with images based on camera capture. currently not implemented
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
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
