package com.my.app.face_catch.services;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class SearchResult {
	private Mat mat;
	private Rect rect;
	
	public SearchResult(Mat mat, Rect rect) {
		this.rect = rect;
		this.mat = mat;
	}
	public Mat getMat() {
		return mat;
	}
	public void setMat(Mat mat) {
		this.mat = mat;
	}
	public Rect getRect() {
		return rect;
	}
	public void setRect(Rect rect) {
		this.rect = rect;
	}
	
	
}
