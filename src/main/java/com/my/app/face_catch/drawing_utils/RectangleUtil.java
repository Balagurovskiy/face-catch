package com.my.app.face_catch.drawing_utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.my.app.face_catch.Property;
/**
* Rectangle drawing utility that implement DrawingUtils interface and based on opencv
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public class RectangleUtil implements DrawingUtils {

	
	private final int FILLED = Imgproc.FILLED;
	private final int BORDERED = 2;
	
	private int thikness;
    /**
     * Constructor. Inits opencv Core.
     * @param nothing
     * @return nothing 
     */
	public RectangleUtil() {
		thikness = BORDERED;
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	}
    /**
     * Create rectangle based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param target - rectangle parameters
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Rect target, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			target,
			color,
			thikness
		);
	}
	 /**
     * Create rectangle based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param dist - distance that will be applied to the start point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Point start, int dist, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			start,
			new Point(start.x + dist, start.y + dist),
			color,
			thikness
		);
	}
	 /**
     * Create rectangle based on input parameters with thickness 2 
     * @param src - base matrix (where rectangle will be placed)
     * @param start - left corner point
     * @param end - right bottom corner point
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void draw(Mat src, Point start, Point end, Scalar color) {
		Imgproc.rectangle(
			src,                                              
			start,
			end,
			color,
			thikness
		);
	}
	 /**
     * Create rectangle based on input parameters with thickness Imgproc.FILLED
     * @param src - base matrix (where rectangle will be placed)
     * @param target - rectangle parameters
     * @param color - color of rectangle border
     * @return nothing 
     */
	@Override
	public void drawFilled(Mat src, Rect target, Scalar color) {
		thikness = FILLED;
		draw(src, target, color);
		thikness = BORDERED;
	}
	 /**
     * Create rectangle based on input parameters with thickness Imgproc.FILLED
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
		thikness = BORDERED;
	}
	 /**
     * Create rectangle based on input parameters with thickness Imgproc.FILLED
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
		thikness = BORDERED;
	}
 
}
