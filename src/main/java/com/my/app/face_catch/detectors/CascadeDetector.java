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
    
    private CascadeClassifier classifier;
    private boolean active;
    
    public CascadeDetector() {
    	active = false;
	}
    
	public CascadeDetector(String imageFilePath) {
		active = true;

	    try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	    	classifier = new CascadeClassifier( imageFilePath );
	    }catch (UnsatisfiedLinkError e) {
	    	System.out.println("CASCADE CLASSIFIER WAS NOT SETTED - OPEN CV CORE NOT INITIALIZED");
	    	active = false;
		}catch (NullPointerException e) {
			System.out.println("CASCADE CLASSIFIER WAS NOT SETTED - THERE IS NO CASCADE.XML FILE SOURCE");
			active = false;
		}
	}

	@Override
	public List<Rect> find(Mat mat) {
		if (active) {
			
			MatOfRect faceDetections = new MatOfRect();
			
			 try {
				 classifier.detectMultiScale(mat, faceDetections);
			}catch (NullPointerException e) {
				System.out.println("CASCADE DETECTOR FAILED - FIND EXCEPTIONE");
			}
			
			return Arrays.asList(faceDetections.toArray());
		}
		return new ArrayList<Rect>();
	}
}
