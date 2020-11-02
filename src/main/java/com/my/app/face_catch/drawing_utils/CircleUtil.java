package com.my.app.face_catch.drawing_utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CircleUtil implements DrawingUtils {

	
	private final int FILLED = Imgproc.FILLED;
	private final int BORDERED = 1;
	
	private int thikness;
	
	public CircleUtil() {
		thikness = BORDERED;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
	
	private int getDistance(int a, int b) {
		return (int) Math.sqrt(
				Math.pow(a, 2) + 
				Math.pow(b, 2)
			);
	}
	
	@Override
	public void draw(Mat src, Rect target, Scalar color) {
		thikness = BORDERED;
		
		int radius = getDistance(target.width, target.height);
		
		Imgproc.circle(
			src,                                              
			new Point(target.x, target.y),
			radius,
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, int dist, Scalar color) {
		thikness = BORDERED;
		Imgproc.circle(
			src,                                              
			start,
			dist,
			color,
			thikness
		);
	}

	@Override
	public void draw(Mat src, Point start, Point end, Scalar color) {
		thikness = BORDERED;
		
		int radius = getDistance((int)(start.x - end.x), (int)(start.y - end.y));
		
		Imgproc.circle(
			src,                                              
			start,
			radius,
			color,
			thikness
		);
	}

	@Override
	public void drawFilled(Mat src, Rect target, Scalar color) {
		thikness = FILLED;
		draw(src, target, color);
	}

	@Override
	public void drawFilled(Mat src, Point start, int dist, Scalar color) {
		thikness = FILLED;
		draw(src, start, dist, color);
	}

	@Override
	public void drawFilled(Mat src, Point start, Point end, Scalar color) {
		thikness = FILLED;
		draw(src, start, end, color);
	}
}
