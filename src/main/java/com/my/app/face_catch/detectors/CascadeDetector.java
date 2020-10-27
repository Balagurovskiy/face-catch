package com.my.app.face_catch.detectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import com.my.app.face_catch.Property;

public class CascadeDetector implements FaceDetector {

	private static final String CASCADE_FILE = "opencv.cascade";
	
    private boolean status;
    
    private CascadeClassifier classifier;
    
	public CascadeDetector(Property property) {
		status = true;

		if (property.getStatus() == false) {
	       //LOG HERE
			System.out.println("CASCADE DETECTOR FAILED - PROPERRTY EXCEPTION");
			status = false;
			return ;
		}
	    try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	    	classifier = new CascadeClassifier( property.getProperty(CASCADE_FILE) );
	    }catch (UnsatisfiedLinkError e) {
	    	status = false;
	       //LOG HERE
			System.out.println("CASCADE CLASSIFIER WAS NOT SETTED - OPEN CV CORE NOT INITIALIZED");
		}catch (NullPointerException e) {
			status = false;
	       //LOG HERE
			System.out.println("CASCADE CLASSIFIER WAS NOT SETTED - THERE IS NO 'opencv.cascade' PROPERTY");
		}
	}

	@Override
	public List<Rect> find(Mat mat) {
		if (status == false || mat == null) {
			//LOG HERE
			System.out.println("CASCADE DETECTOR FAILED - FIND EXCEPTION");
			return new ArrayList<Rect>();
		}
		MatOfRect faceDetections = new MatOfRect();
		classifier.detectMultiScale((Mat)mat, faceDetections);
		
		List<Rect> allFaceMats = Arrays.asList(faceDetections.toArray());
		
		return allFaceMats;
	}
}
