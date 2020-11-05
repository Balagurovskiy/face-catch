package com.my.app.face_catch.drawing_utils;

import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.my.app.face_catch.Property;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
/**
* interface for basic drawings based on opencv
*
* @author  Balagurovskiy
* @version 1.0
* @since   2020-11-4 
*/
public interface DrawingUtils {
	
	void draw(Mat src, Rect target, Scalar color);
	void draw(Mat src, Point start, int dist, Scalar color);
	void draw(Mat src, Point start, Point end, Scalar color);

	void drawFilled(Mat src, Rect target, Scalar color);
	void drawFilled(Mat src, Point start, int dist, Scalar color);
	void drawFilled(Mat src, Point start, Point end, Scalar color);
    /**
     * Scale that multiplies font size
     */
	double FONT_SCALE = 0.5;
    /**
     * Thickness of letters
     */
	int FONT_THICKNESS = 1;
    /**
     * draw text util based on opencv
     * @param src - base matrix where text will be placed
     * @param startPoint - left corner point (start point)
     * @param color - rgb color for text
     * @param text - actual text that will be drawn
     * @return nothing
     */
	default void drawText(Mat src, Point startPoint, Scalar color, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        color,
	        FONT_THICKNESS 
		);
	};
    /**
     * draw text util based on opencv ( white text color by default)
     * @param src - base matrix where text will be placed
     * @param startPoint - left corner point (start point)
     * @param text - actual text that will be drawn
     * @return nothing
     */
	default void drawTextWhite(Mat src, Point startPoint, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        new Scalar(222, 222, 222),
	        FONT_THICKNESS
		);
	};
    /**
     * draw text util based on opencv ( black text color by default)
     * @param src - base matrix where text will be placed
     * @param startPoint - left corner point (start point)
     * @param text - actual text that will be drawn
     * @return nothing
     */
	default void drawTextBlack(Mat src, Point startPoint, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        new Scalar(11, 11, 11),
	        FONT_THICKNESS
		);
	};
}
