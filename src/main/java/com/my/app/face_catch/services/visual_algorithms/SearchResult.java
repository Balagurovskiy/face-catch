package com.my.app.face_catch.services.visual_algorithms;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
/**
* POJO for opencv Mat nad Rect
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class SearchResult {
	
	private final Mat mat;
	private final Rect rect;
	
	public SearchResult(Mat mat, Rect rect) {
		this.rect = rect;
		this.mat = mat;
	}
	public Mat getMat() {
		return mat.clone();
	}
	public Rect getRect() {
		return rect.clone();
	}
	
	@Override
	public int hashCode() {
		return  31 * (mat.hashCode() + rect.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass().equals(obj.getClass()) == false)
			return false;
		if (this == obj)
			return true;
		
		SearchResult that = (SearchResult) obj;
		
		if (mat.cols() == that.getMat().cols() && mat.rows() == that.getMat().rows()) {
			if (rect.height == that.getRect().height && rect.width == that.getRect().width) {
				if (rect.x == that.getRect().x && rect.y == that.getRect().y) {
					return true;
				}
			}
		}
		return false;
	}
}
