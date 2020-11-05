package com.my.app.face_catch.drawing_utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
/**
* Line drawing utility that implement DrawingUtils interface and based on opencv
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class LineUtil implements DrawingUtils{

	
	private final int FILLED = 3;
	private final int BORDERED = 1;
	
	private int thikness;
    /**
     * Constructor. Inits opencv Core.
     * @param nothing
     * @return nothing 
     */
	public LineUtil() {
		thikness = BORDERED;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
    /**
     * Create line based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param target - rectangle parameters
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Rect target, Scalar color) {
		thikness = BORDERED;
		
		Imgproc.line(
			src,                                              
			new Point(target.x, target.y),
			new Point(target.width + target.x, target.height + target.y),
			color,
			thikness
		);
	}
	 /**
     * Create line based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param dist - distance that will be applied to the start point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Point start, int dist, Scalar color) {
		thikness = BORDERED;
		Imgproc.line(
			src,                                              
			start,
			new Point(dist + start.x, dist + start.y),
			color,
			thikness
		);
	}
	 /**
     * Create line based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param end - right bottom corner point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Point start, Point end, Scalar color) {
		thikness = BORDERED;
		
		Imgproc.line(
			src,                                              
			start,
			end,
			color,
			thikness
		);
	}
	 /**
     * Create line based on input parameters with thickness Imgproc.FILLED
     * @param src - base matrix (where rectangle will be placed)
     * @param target - rectangle parameters
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void drawFilled(Mat src, Rect target, Scalar color) {
		thikness = FILLED;
		draw(src, target, color);
	}
	 /**
     * Create line based on input parameters with thickness Imgproc.FILLED
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param dist - distance that will be applied to the start point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void drawFilled(Mat src, Point start, int dist, Scalar color) {
		thikness = FILLED;
		draw(src, start, dist, color);
	}
	 /**
     * Create line based on input parameters with thickness Imgproc.FILLED
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param end - right bottom corner point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void drawFilled(Mat src, Point start, Point end, Scalar color) {
		thikness = FILLED;
		draw(src, start, end, color);
	}
}
