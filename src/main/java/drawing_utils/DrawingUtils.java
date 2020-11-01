package drawing_utils;

import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public interface DrawingUtils {
	
	void draw(Mat src, Rect target, Scalar color);
	void draw(Mat src, Point start, int dist, Scalar color);
	void draw(Mat src, Point start, Point end, Scalar color);

	void drawFilled(Mat src, Rect target, Scalar color);
	void drawFilled(Mat src, Point start, int dist, Scalar color);
	void drawFilled(Mat src, Point start, Point end, Scalar color);
	
	double FONT_SCALE = 0.5;
	int FONT_THICNESS = 1;
	
	default void drawText(Mat src, Point startPoint, Scalar color, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        color,
	        FONT_THICNESS 
		);
	};
	default void drawTextWhite(Mat src, Point startPoint, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        new Scalar(222, 222, 222),
	        FONT_THICNESS
		);
	};
	default void drawTextBlack(Mat src, Point startPoint, String text) {
		Imgproc.putText (
	        src,
	        text,
	        startPoint,
	        Imgproc.FONT_HERSHEY_SIMPLEX ,
	        FONT_SCALE,
	        new Scalar(11, 11, 11),
	        FONT_THICNESS
		);
	};
}
