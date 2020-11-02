package com.my.app.face_catch.services;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import com.my.app.face_catch.services.search_algorithm.VisualSearchAlgorithm;

public class ImageService implements VisualService {
	
	private Mat image;
	private boolean active;
	private VisualSearchAlgorithm vsa;
	
	public ImageService() {
		active = false;
	}
	
	public ImageService(String imageFilePath, VisualSearchAlgorithm vsa) {
		active = true;
		if (vsa == null) {
	    	System.out.println("IMAGE PROCESSOR WAS NOT SETTED - VisualSearchAlgorithm IS NULL");
	    	active = false;
	    	return ;
		} else {
			this.vsa = vsa;
		}
	    try {
			System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
			image = Imgcodecs.imread( imageFilePath );
	    }catch (UnsatisfiedLinkError e) {
	    	System.out.println("IMAGE PROCESSOR WAS NOT SETTED - OPEN CV CORE NOT INITIALIZED");
	    	active = false;
		}catch (NullPointerException e) {
			System.out.println("IMAGE PROCESSOR WAS NOT SETTED - THERE IS NO IMAGE SOURCE");
			active = false;
		}
	}
	
	public Mat getImage() {
		return image;
	}
	
	// refactor 
	// - bd load here
	// - compare 
	// - draw info on matrix
	// **** remove return value ?????
	@Override
	public List<SearchResult> executeSearch() {
		if (active) {
			return vsa.find(image)
					.stream()
					.map(
							rect -> new SearchResult(getImage().submat(rect), rect)
						)
					.collect(Collectors.toList());
			
			
		}
		return new ArrayList<SearchResult>();
	}

	@Override
	public void export(String path) {
		if (active) {
		   Imgcodecs.imwrite(path , image);
		}
	}
}
